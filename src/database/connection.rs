use sqlx::MySqlPool;
use crate::config::DatabaseConfig;

/// 创建数据库连接池
pub async fn create_pool(config: &DatabaseConfig) -> anyhow::Result<MySqlPool> {
    let pool = MySqlPool::connect(&config.url).await?;
    Ok(pool)
}
