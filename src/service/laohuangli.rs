use chrono::NaiveDate;
use teloxide::types::User;
use crate::service::RedisService;
use crate::database::repository::{LogRepository, YijiRepository, LunarRepository};
use crate::util::date::format_pure_date;
use crate::util::random::next_int_from_seed;
use crate::util::secure::md5;
use crate::config::TelegramConfig;

/// 老黄历核心服务
pub struct LaohuangliService {
    redis_service: RedisService,
    log_repository: LogRepository,
    yiji_repository: YijiRepository,
    lunar_repository: LunarRepository,
    bot_config: TelegramConfig,
}

impl LaohuangliService {
    pub fn new(
        redis_service: RedisService,
        log_repository: LogRepository,
        yiji_repository: YijiRepository,
        lunar_repository: LunarRepository,
        bot_config: TelegramConfig,
    ) -> Self {
        Self {
            redis_service,
            log_repository,
            yiji_repository,
            lunar_repository,
            bot_config,
        }
    }

    /// 获取今日老黄历
    pub async fn get_log(&self) -> anyhow::Result<String> {
        let today = crate::util::date::today();
        self.get_log_by_date(today).await
    }

    /// 根据日期获取老黄历
    /// 
    /// 与Java版本保持一致：如果数据库中没有记录，会自动生成并保存
    pub async fn get_log_by_date(&self, date: NaiveDate) -> anyhow::Result<String> {
        // 先尝试从 Redis 获取
        if let Some(log) = self.redis_service.get_log_by_date(date).await? {
            return Ok(log.content);
        }

        // 从数据库获取
        if let Some(content) = self.log_repository.get_content_by_date(date).await? {
            return Ok(content);
        }

        // 数据库中没有记录时，生成并保存（与Java版本保持一致）
        let content = self.generate_log_content(date).await?;
        let log = self.log_repository.insert(date, &content).await?;
        
        // 保存到Redis
        self.redis_service.set_log_by_date(&log, date).await?;
        
        Ok(content)
    }

    /// 获取个人老黄历
    pub async fn get_personal_log(&self, user: &User) -> anyhow::Result<String> {
        let today = crate::util::date::today();
        
        // 先尝试从 Redis 获取
        if let Some(personal_log) = self.redis_service.get_personal_log(today, user).await? {
            if !personal_log.is_empty() {
                return Ok(personal_log);
            }
        }

        // 生成个人老黄历
        self.generate_personal_log(user).await
    }

    /// 生成个人老黄历
    /// 
    /// 改进的随机方式（不使用数据库OFFSET）：
    /// 1. 使用日期、用户ID和Bot Token生成MD5哈希作为随机种子
    /// 2. 从MD5哈希值生成随机整数作为种子
    /// 3. 先获取所有宜忌数据，然后在应用层使用随机索引选择
    ///    这种方式比数据库OFFSET更灵活，可以避免重复选择，随机性更好
    async fn generate_personal_log(&self, user: &User) -> anyhow::Result<String> {
        let user_id = user.id.0;
        let user_full_name = crate::util::telegram::get_user_full_name(user);
        let prefix = format!("{} 今日：", user_full_name);

        let today = crate::util::date::today();
        
        // 生成随机种子
        // 使用日期、用户ID和Bot Token生成MD5哈希作为随机种子
        let token = self.bot_config.bot_token.replace(":", "");
        let date_str = format_pure_date(&today);
        let random_seed = format!("{}{}{}", date_str, user_id, token);
        let seed_hash = md5(&random_seed);
        
        // 使用种子生成随机整数作为随机数生成器的种子
        let seed_bytes = seed_hash.as_bytes();
        let seed = next_int_from_seed(seed_bytes);
        
        // 从数据库获取宜忌数据（使用应用层随机选择，而不是数据库OFFSET）
        let yiji_list = self.yiji_repository.get_random(seed, 2).await?;
        
        if yiji_list.len() < 2 {
            return Err(anyhow::anyhow!("获取宜忌数据不足"));
        }

        // 格式化结果：第一个是宜项，第二个是忌项
        let yi = yiji_list[0].yi.as_deref().unwrap_or("");
        let ji = yiji_list[1].ji.as_deref().unwrap_or("");
        let result = format!("{}宜{}，忌{}。", prefix, yi, ji);

        // 缓存到 Redis
        self.redis_service
            .set_personal_log(today, user, &result)
            .await?;

        Ok(result)
    }

    /// 生成老黄历内容
    /// 
    /// 与Java版本的generateLogContent方法保持一致：
    /// 1. 获取农历信息
    /// 2. 生成日期、星期、农历、节气等信息
    /// 3. 使用日期+token生成随机种子
    /// 4. 调用get_daily_random获取6个宜忌项（4个宜项+2个忌项）
    /// 5. 格式化为："宜xxx，宜xxx，宜xxx，宜xxx，宜交配；忌xxx，忌xxx。"
    async fn generate_log_content(&self, date: NaiveDate) -> anyhow::Result<String> {
        use chrono::Datelike;
        
        let mut content = String::new();
        
        // 获取农历信息
        if let Some(lunar) = self.lunar_repository.get_by_date(date).await? {
            // 星期中文数组
            let chinese_number = ["一", "二", "三", "四", "五", "六", "日"];
            let weekday_index = date.weekday().num_days_from_monday() as usize;
            
            // 日期信息
            content.push_str(&format!(
                "{}年{}月{}日，星期{}，农历{}{}",
                date.year(),
                date.month(),
                date.day(),
                chinese_number[weekday_index],
                lunar.l_month.as_deref().unwrap_or(""),
                lunar.l_day.as_deref().unwrap_or("")
            ));
            
            // 节气信息
            if let Some(solar_term) = &lunar.solar_term_name {
                if !solar_term.is_empty() {
                    content.push_str("，");
                    content.push_str(solar_term);
                }
            }
            
            content.push_str("。\n");
            
            // 干支信息
            content.push_str("干支：");
            if let (Some(year), Some(month), Some(day)) = (
                &lunar.tian_gan_di_zhi_year,
                &lunar.tian_gan_di_zhi_month,
                &lunar.tian_gan_di_zhi_day,
            ) {
                content.push_str(&format!("{}年，{}月，{}日。\n", year, month, day));
            }
            
            // 五行信息
            content.push_str("五行：");
            if let (Some(wuxing), Some(jianshen)) = (&lunar.wuxing_na_day, &lunar.jian_shen) {
                content.push_str(&format!("{}，{}执位。\n", wuxing, jianshen));
            }
            
            // 冲煞信息
            if let (Some(chong), Some(suisha)) = (&lunar.chong, &lunar.sui_sha) {
                content.push_str(&format!("{}，{}。\n", chong, suisha));
            }
        }
        
        // 生成随机种子（使用日期+token）
        let token = self.bot_config.bot_token.replace(":", "");
        let date_str = format_pure_date(&date);
        let random_seed = format!("{}{}", date_str, token);
        let seed_hash = md5(&random_seed);
        
        // 使用种子生成随机整数
        let seed_bytes = seed_hash.as_bytes();
        let seed = next_int_from_seed(seed_bytes);
        
        // 获取每日老黄历的随机宜忌（4个宜项+2个忌项）
        let yiji_list = self.yiji_repository.get_daily_random(seed).await?;
        
        if yiji_list.len() < 6 {
            return Err(anyhow::anyhow!("获取宜忌数据不足，需要6个，实际{}个", yiji_list.len()));
        }
        
        // 格式化结果（与Java版本保持一致）
        // Java: "宜%s，宜%s，宜%s，宜%s，宜交配；忌%s，忌%s。"
        let yi0 = yiji_list[0].yi.as_deref().unwrap_or("");
        let yi1 = yiji_list[1].yi.as_deref().unwrap_or("");
        let yi2 = yiji_list[2].yi.as_deref().unwrap_or("");
        let yi3 = yiji_list[3].yi.as_deref().unwrap_or("");
        let ji0 = yiji_list[4].ji.as_deref().unwrap_or("");
        let ji1 = yiji_list[5].ji.as_deref().unwrap_or("");
        content.push_str(&format!(
            "宜{}，宜{}，宜{}，宜{}，宜交配；忌{}，忌{}。",
            yi0, yi1, yi2, yi3, ji0, ji1
        ));
        
        Ok(content)
    }
}
