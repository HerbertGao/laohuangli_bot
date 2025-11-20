use serde::Deserialize;

/// Telegram Bot 配置
#[derive(Debug, Clone, Deserialize)]
pub struct TelegramConfig {
    pub bot_token: String,
    pub channel_id: String,
}

/// 数据库配置
#[derive(Debug, Clone, Deserialize)]
pub struct DatabaseConfig {
    pub url: String,
}

/// Redis 配置
#[derive(Debug, Clone, Deserialize)]
pub struct RedisConfig {
    pub url: String,
}

/// 应用配置
#[derive(Debug, Clone, Deserialize)]
pub struct Config {
    pub telegram: TelegramConfig,
    pub database: DatabaseConfig,
    pub redis: RedisConfig,
}

impl Config {
    /// 从环境变量加载配置
    /// 支持多环境配置：
    /// - 如果设置了 APP_ENV 环境变量，会加载对应的 .env.{env} 文件
    /// - 否则优先加载 .env 文件，如果不存在则尝试加载默认配置
    pub fn from_env() -> anyhow::Result<Self> {
        // 获取环境类型（dev, online, online-beta）
        let env = std::env::var("APP_ENV").unwrap_or_else(|_| "dev".to_string());
        
        // 根据环境加载对应的配置文件
        let env_file = format!(".env.{}", env);
        
        // 尝试加载环境特定的配置文件
        if std::path::Path::new(&env_file).exists() {
            dotenv::from_filename(&env_file).ok();
            tracing::info!("加载环境配置文件: {}", env_file);
        } else {
            // 如果环境特定文件不存在，尝试加载默认 .env 文件
            dotenv::dotenv().ok();
            tracing::info!("加载默认配置文件: .env");
        }
        
        Ok(Config {
            telegram: TelegramConfig {
                bot_token: std::env::var("TELEGRAM_BOT_TOKEN")
                    .map_err(|_| anyhow::anyhow!("TELEGRAM_BOT_TOKEN 未设置，请检查配置文件"))?,
                channel_id: std::env::var("TELEGRAM_CHANNEL_ID")
                    .map_err(|_| anyhow::anyhow!("TELEGRAM_CHANNEL_ID 未设置，请检查配置文件"))?,
            },
            database: DatabaseConfig {
                url: std::env::var("DATABASE_URL")
                    .map_err(|_| anyhow::anyhow!("DATABASE_URL 未设置，请检查配置文件"))?,
            },
            redis: RedisConfig {
                url: std::env::var("REDIS_URL")
                    .unwrap_or_else(|_| "redis://127.0.0.1:6379/0".to_string()),
            },
        })
    }

    /// 从指定的环境文件加载配置
    pub fn from_env_file(env: &str) -> anyhow::Result<Self> {
        // 根据环境加载对应的配置文件
        let env_file = format!(".env.{}", env);
        
        // 尝试加载环境特定的配置文件
        if std::path::Path::new(&env_file).exists() {
            dotenv::from_filename(&env_file).ok();
            tracing::info!("加载环境配置文件: {}", env_file);
        } else {
            return Err(anyhow::anyhow!("配置文件不存在: {}", env_file));
        }
        
        Ok(Config {
            telegram: TelegramConfig {
                bot_token: std::env::var("TELEGRAM_BOT_TOKEN")
                    .map_err(|_| anyhow::anyhow!("TELEGRAM_BOT_TOKEN 未设置，请检查配置文件"))?,
                channel_id: std::env::var("TELEGRAM_CHANNEL_ID")
                    .map_err(|_| anyhow::anyhow!("TELEGRAM_CHANNEL_ID 未设置，请检查配置文件"))?,
            },
            database: DatabaseConfig {
                url: std::env::var("DATABASE_URL")
                    .map_err(|_| anyhow::anyhow!("DATABASE_URL 未设置，请检查配置文件"))?,
            },
            redis: RedisConfig {
                url: std::env::var("REDIS_URL")
                    .unwrap_or_else(|_| "redis://127.0.0.1:6379/0".to_string()),
            },
        })
    }
}
