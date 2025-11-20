use sqlx::MySqlPool;
use chrono::NaiveDate;
use crate::database::models::{Log, Yiji, Lunar};

/// Log 数据访问层
pub struct LogRepository {
    pool: MySqlPool,
}

impl LogRepository {
    pub fn new(pool: MySqlPool) -> Self {
        Self { pool }
    }

    /// 根据日期获取 Log
    pub async fn get_by_date(&self, date: NaiveDate) -> anyhow::Result<Option<Log>> {
        let log = sqlx::query_as::<_, Log>(
            "SELECT id, `date`, content FROM laohuangli.log WHERE `date` = ?"
        )
        .bind(date)
        .fetch_optional(&self.pool)
        .await?;
        
        Ok(log)
    }

    /// 获取 Log 内容（仅返回字符串）
    pub async fn get_content_by_date(&self, date: NaiveDate) -> anyhow::Result<Option<String>> {
        let content: Option<String> = sqlx::query_scalar(
            "SELECT content FROM laohuangli.log WHERE `date` = ?"
        )
        .bind(date)
        .fetch_optional(&self.pool)
        .await?;
        
        Ok(content)
    }

    /// 插入日志
    pub async fn insert(&self, date: NaiveDate, content: &str) -> anyhow::Result<Log> {
        sqlx::query(
            "INSERT INTO laohuangli.log (`date`, content) VALUES (?, ?)"
        )
        .bind(date)
        .bind(content)
        .execute(&self.pool)
        .await?;

        // 返回插入后的记录
        self.get_by_date(date).await?
            .ok_or_else(|| anyhow::anyhow!("插入日志后查询失败"))
    }
}

/// Yiji 数据访问层
pub struct YijiRepository {
    pool: MySqlPool,
}

impl YijiRepository {
    pub fn new(pool: MySqlPool) -> Self {
        Self { pool }
    }

    /// 获取所有宜项（yi字段不为空）
    pub async fn get_all_yi(&self) -> anyhow::Result<Vec<Yiji>> {
        let yiji_list = sqlx::query_as::<_, Yiji>(
            "SELECT id, catalog, yi, ji FROM laohuangli.yiji WHERE yi IS NOT NULL AND yi != ''"
        )
        .fetch_all(&self.pool)
        .await?;
        
        Ok(yiji_list)
    }

    /// 获取所有忌项（ji字段不为空）
    pub async fn get_all_ji(&self) -> anyhow::Result<Vec<Yiji>> {
        let yiji_list = sqlx::query_as::<_, Yiji>(
            "SELECT id, catalog, yi, ji FROM laohuangli.yiji WHERE ji IS NOT NULL AND ji != ''"
        )
        .fetch_all(&self.pool)
        .await?;
        
        Ok(yiji_list)
    }

    /// 根据随机种子从所有数据中随机选择
    /// 
    /// 改进的随机方式（不使用数据库OFFSET）：
    /// 1. 先获取所有符合条件的宜忌数据
    /// 2. 使用随机数生成索引
    /// 3. 在内存中选择，避免重复
    /// 
    /// 这种方式比数据库OFFSET更灵活：
    /// - 可以确保选择的随机性更好
    /// - 可以避免重复选择
    /// - 可以按分类选择
    pub async fn get_random(&self, seed: i32, count: usize) -> anyhow::Result<Vec<Yiji>> {
        use rand::{Rng, SeedableRng};
        use rand::rngs::StdRng;
        
        // 创建基于种子的随机数生成器
        let mut rng = StdRng::seed_from_u64(seed.abs() as u64);
        
        // 分别获取宜和忌的数据
        let mut yi_list = self.get_all_yi().await?;
        let mut ji_list = self.get_all_ji().await?;
        
        if yi_list.is_empty() {
            return Err(anyhow::anyhow!("宜项数据为空"));
        }
        if ji_list.is_empty() {
            return Err(anyhow::anyhow!("忌项数据为空"));
        }
        
        let mut result = Vec::new();
        
        // 按需选择：偶数索引选宜项，奇数索引选忌项
        // 例如：count=2 时，选择1个宜项和1个忌项
        for i in 0..count {
            if i % 2 == 0 {
                // 选择宜项
                if yi_list.is_empty() {
                    return Err(anyhow::anyhow!("宜项数据不足"));
                }
                let random_index = rng.random_range(0..yi_list.len());
                result.push(yi_list.remove(random_index));
            } else {
                // 选择忌项
                if ji_list.is_empty() {
                    return Err(anyhow::anyhow!("忌项数据不足"));
                }
                let random_index = rng.random_range(0..ji_list.len());
                result.push(ji_list.remove(random_index));
            }
        }
        
        Ok(result)
    }

    /// 获取每日老黄历的随机宜忌（按分类选择）
    /// 
    /// 与Java版本的getDailyYijiRandom方法一致：
    /// 1. 按分类（衣、食、住、行）各选1个宜项，共4个
    /// 2. 然后选择2个忌项（排除已选择的宜项）
    /// 3. 总共返回6个Yiji
    pub async fn get_daily_random(&self, seed: i32) -> anyhow::Result<Vec<Yiji>> {
        use rand::{Rng, SeedableRng};
        use rand::rngs::StdRng;
        
        // 创建基于种子的随机数生成器
        let mut rng = StdRng::seed_from_u64(seed.abs() as u64);
        
        let catalog_list = ["衣", "食", "住", "行"];
        let mut result = Vec::new();
        let mut selected_ids = Vec::new();
        
        // 宜 - 按分类选择
        for catalog in &catalog_list {
            let mut yi_list = sqlx::query_as::<_, Yiji>(
                "SELECT id, catalog, yi, ji FROM laohuangli.yiji WHERE catalog = ? AND yi IS NOT NULL AND yi != ''"
            )
            .bind(catalog)
            .fetch_all(&self.pool)
            .await?;
            
            if !yi_list.is_empty() {
                let random_index = rng.random_range(0..yi_list.len());
                let selected = yi_list.remove(random_index);
                if let Some(id) = selected.id {
                    selected_ids.push(id);
                }
                result.push(selected);
            }
        }
        
        // 忌 - 排除已选择的宜项
        let mut ji_list = if selected_ids.is_empty() {
            sqlx::query_as::<_, Yiji>(
                "SELECT id, catalog, yi, ji FROM laohuangli.yiji WHERE ji IS NOT NULL AND ji != ''"
            )
            .fetch_all(&self.pool)
            .await?
        } else {
            // 构建NOT IN查询
            let placeholders = selected_ids.iter().map(|_| "?").collect::<Vec<_>>().join(",");
            let query = format!(
                "SELECT id, catalog, yi, ji FROM laohuangli.yiji WHERE ji IS NOT NULL AND ji != '' AND id NOT IN ({})",
                placeholders
            );
            let mut query_builder = sqlx::query_as::<_, Yiji>(&query);
            for id in &selected_ids {
                query_builder = query_builder.bind(id);
            }
            query_builder.fetch_all(&self.pool).await?
        };
        
        // 随机选择2个忌项
        for _ in 0..2 {
            if ji_list.is_empty() {
                break;
            }
            let random_index = rng.random_range(0..ji_list.len());
            result.push(ji_list.remove(random_index));
        }
        
        Ok(result)
    }
}

/// Lunar 数据访问层
pub struct LunarRepository {
    pool: MySqlPool,
}

impl LunarRepository {
    pub fn new(pool: MySqlPool) -> Self {
        Self { pool }
    }

    /// 根据日期获取农历信息
    pub async fn get_by_date(&self, date: NaiveDate) -> anyhow::Result<Option<Lunar>> {
        let lunar = sqlx::query_as::<_, Lunar>(
            "SELECT GregorianDateTime, LunarDateTime, LunarShow, LMonth, LDay, SolarTermName, TianGanDiZhiYear, TianGanDiZhiMonth, TianGanDiZhiDay, WuxingNaDay, JianShen, Chong, SuiSha FROM laohuangli.lunar WHERE GregorianDateTime = ? LIMIT 1"
        )
        .bind(date)
        .fetch_optional(&self.pool)
        .await?;
        
        Ok(lunar)
    }
}
