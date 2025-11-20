use redis::AsyncCommands;
use redis::Client as RedisClient;
use chrono::NaiveDate;
use teloxide::types::User;
use crate::constants::{LOG_PREFIX, PERSONAL_LOG_PREFIX};
use crate::util::date::format_normal;
use crate::database::models::Log;
use serde_json;

/// Redis 缓存服务
pub struct RedisService {
    client: RedisClient,
}

impl RedisService {
    pub fn new(url: &str) -> anyhow::Result<Self> {
        let client = RedisClient::open(url)?;
        Ok(Self { client })
    }

    /// 获取连接
    async fn get_connection(&self) -> anyhow::Result<redis::aio::MultiplexedConnection> {
        let manager = self.client.get_multiplexed_async_connection().await?;
        Ok(manager)
    }

    /// 根据日期获取 Log
    pub async fn get_log_by_date(&self, date: NaiveDate) -> anyhow::Result<Option<Log>> {
        let mut conn = self.get_connection().await?;
        let key = format!("{}{}", LOG_PREFIX, format_normal(&date));
        
        let value: Option<String> = conn.get(&key).await?;
        if let Some(json_str) = value {
            match serde_json::from_str::<Log>(&json_str) {
                Ok(log) => Ok(Some(log)),
                Err(e) => {
                    tracing::error!("反序列化 Log 失败: {}", e);
                    Ok(None)
                }
            }
        } else {
            Ok(None)
        }
    }

    /// 设置 Log
    pub async fn set_log_by_date(&self, log: &Log, date: NaiveDate) -> anyhow::Result<()> {
        let mut conn = self.get_connection().await?;
        let key = format!("{}{}", LOG_PREFIX, format_normal(&date));
        
        match serde_json::to_string(log) {
            Ok(json_str) => {
                let _: () = conn.set(&key, json_str).await?;
                Ok(())
            }
            Err(e) => {
                tracing::error!("序列化 Log 失败: {}", e);
                Err(anyhow::anyhow!("序列化失败"))
            }
        }
    }

    /// 获取个人老黄历
    pub async fn get_personal_log(&self, date: NaiveDate, user: &User) -> anyhow::Result<Option<String>> {
        let mut conn = self.get_connection().await?;
        let key = format!("{}{}:{}", PERSONAL_LOG_PREFIX, format_normal(&date), user.id);
        
        let value: Option<String> = conn.get(&key).await?;
        Ok(value)
    }

    /// 设置个人老黄历
    pub async fn set_personal_log(&self, date: NaiveDate, user: &User, content: &str) -> anyhow::Result<()> {
        let mut conn = self.get_connection().await?;
        let key = format!("{}{}:{}", PERSONAL_LOG_PREFIX, format_normal(&date), user.id);
        
        let _: () = conn.set(&key, content).await?;
        Ok(())
    }
}
