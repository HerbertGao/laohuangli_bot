use teloxide::{Bot, prelude::Requester};
use tokio_cron_scheduler::{Job, JobScheduler};
use chrono::Duration;
use chrono_tz::Asia::Shanghai;
use std::sync::Arc;
use crate::service::LaohuangliService;

/// 每日任务调度器
pub struct DailyTaskScheduler {
    laohuangli_service: Arc<LaohuangliService>,
    bot: Bot,
    channel_id: String,
}

impl DailyTaskScheduler {
    pub fn new(
        laohuangli_service: Arc<LaohuangliService>,
        bot: Bot,
        channel_id: String,
    ) -> Self {
        Self {
            laohuangli_service,
            bot,
            channel_id,
        }
    }

    /// 设置定时任务
    pub async fn setup(&self) -> anyhow::Result<JobScheduler> {
        let sched = JobScheduler::new().await?;

        // 每日生成任务：每天 00:00（中国时区）生成未来 14 天的老黄历
        let laohuangli_service = Arc::clone(&self.laohuangli_service);
        sched
            .add(
                Job::new_async_tz("0 0 0 * * *", Shanghai, move |_uuid, _l| {
                    let service = Arc::clone(&laohuangli_service);
                    Box::pin(async move {
                        tracing::info!("开始生成老黄历日志");
                        let generate_days = 14;
                        let mut generate_date = crate::util::date::today();
                        
                        for _ in 1..generate_days {
                            tracing::info!("生成中, 日期:{}", crate::util::date::format_normal(&generate_date));
                            if let Err(e) = service.get_log_by_date(generate_date).await {
                                tracing::error!("生成老黄历失败 {}: {}", generate_date, e);
                            }
                            generate_date = generate_date + Duration::days(1);
                        }
                        tracing::info!("结束生成老黄历日志");
                    })
                })
                .unwrap(),
            )
            .await?;

        // 定时发送任务：每天 22:00（中国时区）发送次日老黄历到频道
        let bot = self.bot.clone();
        let channel_id = self.channel_id.clone();
        let laohuangli_service = Arc::clone(&self.laohuangli_service);
        sched
            .add(
                Job::new_async_tz("0 0 22 * * *", Shanghai, move |_uuid, _l| {
                    let bot = bot.clone();
                    let channel_id = channel_id.clone();
                    let service = Arc::clone(&laohuangli_service);
                    Box::pin(async move {
                        let date = crate::util::date::today() + Duration::days(1);
                        match service.get_log_by_date(date).await {
                            Ok(msg) => {
                                if let Err(e) = bot.send_message(
                                    teloxide::types::ChatId(channel_id.parse::<i64>().unwrap_or(0)),
                                    msg,
                                ).await {
                                    tracing::error!("发送消息失败: {}", e);
                                }
                            }
                            Err(e) => {
                                tracing::error!("获取老黄历失败: {}", e);
                            }
                        }
                    })
                })
                .unwrap(),
            )
            .await?;

        sched.start().await?;
        Ok(sched)
    }
}
