mod config;
mod constants;
mod util;
mod database;
mod service;
mod bot;
mod task;
mod updater;

use std::sync::Arc;
use teloxide::prelude::*;
use clap::{Parser, Subcommand};
use crate::config::Config;
use crate::database::{create_pool, LogRepository, YijiRepository, LunarRepository};
use crate::service::{RedisService, LaohuangliService, MessageService, InlineQueryService};
use crate::bot::BotHandler;
use crate::task::DailyTaskScheduler;
use crate::updater::Updater;

#[derive(Parser)]
#[command(name = "laohuangli_bot")]
#[command(about = "青年老黄历Bot")]
#[command(version)]
struct Cli {
    /// 环境配置文件（dev, online, online-beta），默认使用 APP_ENV 或 .env
    #[arg(short, long)]
    env: Option<String>,
    
    #[command(subcommand)]
    command: Option<Commands>,
}

#[derive(Subcommand)]
enum Commands {
    /// 检查并更新到最新版本
    Update,
    /// 启动 Bot
    Start {
        /// 环境配置文件（dev, online, online-beta）
        #[arg(short, long)]
        env: Option<String>,
    },
}

#[tokio::main]
async fn main() -> anyhow::Result<()> {
    // 初始化日志
    tracing_subscriber::fmt()
        .with_env_filter(tracing_subscriber::EnvFilter::from_default_env())
        .init();

    let cli = Cli::parse();

    // 处理命令行命令
    match cli.command {
        Some(Commands::Update) => {
            let updater = Updater::new()?;
            updater.update().await?;
            return Ok(());
        }
        Some(Commands::Start { env }) => {
            let config = if let Some(env_name) = env {
                Config::from_env_file(&env_name)?
            } else {
                Config::from_env()?
            };
            return run_bot(config).await;
        }
        None => {
            // 默认行为：启动 Bot
            let config = if let Some(env_name) = cli.env {
                Config::from_env_file(&env_name)?
            } else {
                Config::from_env()?
            };
            run_bot(config).await
        }
    }
}

async fn run_bot(config: Config) -> anyhow::Result<()> {
    tracing::info!("启动青年老黄历Bot...");
    tracing::info!("配置加载成功");

    // 创建数据库连接池
    let pool = create_pool(&config.database).await?;
    tracing::info!("数据库连接成功");

    // 创建 Redis 服务
    let redis_service = RedisService::new(&config.redis.url)?;
    tracing::info!("Redis 连接成功");

    // 创建数据仓库
    let log_repository = LogRepository::new(pool.clone());
    let yiji_repository = YijiRepository::new(pool.clone());
    let lunar_repository = LunarRepository::new(pool);

    // 创建服务（使用 Arc 共享）
    let laohuangli_service = Arc::new(LaohuangliService::new(
        redis_service,
        log_repository,
        yiji_repository,
        lunar_repository,
        config.telegram.clone(),
    ));

    // 创建子服务（它们内部使用 Arc 共享 laohuangli_service）
    let message_service = MessageService::new(Arc::clone(&laohuangli_service));
    let inline_query_service = InlineQueryService::new(Arc::clone(&laohuangli_service));

    // 创建 Bot（直接使用配置中的 token）
    let bot = Bot::new(&config.telegram.bot_token);

    // 创建 Bot 处理器
    let handler = BotHandler::new(message_service, inline_query_service);

    // 设置定时任务
    let task_scheduler = DailyTaskScheduler::new(
        Arc::clone(&laohuangli_service),
        bot.clone(),
        config.telegram.channel_id.clone(),
    );
    let _scheduler = task_scheduler.setup().await?;
    tracing::info!("定时任务已启动");

    // 启动 Bot
    tracing::info!("Bot 启动中...");
    
    let handler = Arc::new(handler);
    
    // 处理消息命令
    let handler_msg = Arc::clone(&handler);
    let msg_handler = Update::filter_message().endpoint(move |bot: Bot, msg: Message| {
        let handler = Arc::clone(&handler_msg);
        async move {
            handler.handle_message(bot, msg).await
        }
    });
    
    // 处理内联查询
    let handler_inline = Arc::clone(&handler);
    let inline_handler = Update::filter_inline_query().endpoint(move |bot: Bot, query: teloxide::types::InlineQuery| {
        let handler = Arc::clone(&handler_inline);
        async move {
            handler.handle_inline_query(bot, query).await
        }
    });
    
    Dispatcher::builder(bot, dptree::entry().branch(msg_handler).branch(inline_handler))
        .dependencies(dptree::deps![])
        .enable_ctrlc_handler()
        .build()
        .dispatch()
        .await;

    Ok(())
}
