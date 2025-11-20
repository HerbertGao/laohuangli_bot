use chrono::NaiveDate;
use serde::{Deserialize, Serialize};

/// Log 表实体
#[derive(Debug, Clone, Serialize, Deserialize, sqlx::FromRow)]
pub struct Log {
    pub id: Option<u32>,
    pub date: NaiveDate,
    pub content: String,
}

/// Yiji 表实体
#[derive(Debug, Clone, Serialize, Deserialize, sqlx::FromRow)]
pub struct Yiji {
    pub id: Option<u32>,
    pub catalog: Option<String>,
    pub yi: Option<String>,  // 数据库中可能为 NULL
    pub ji: Option<String>,  // 数据库中可能为 NULL
}

/// Lunar 表实体
#[derive(Debug, Clone, Serialize, Deserialize, sqlx::FromRow)]
pub struct Lunar {
    #[sqlx(rename = "GregorianDateTime")]
    pub gregorian_datetime: NaiveDate,
    #[sqlx(rename = "LunarDateTime")]
    pub lunar_datetime: Option<String>,
    #[sqlx(rename = "LunarShow")]
    pub lunar_show: Option<String>,
    #[sqlx(rename = "LMonth")]
    pub l_month: Option<String>,
    #[sqlx(rename = "LDay")]
    pub l_day: Option<String>,
    #[sqlx(rename = "SolarTermName")]
    pub solar_term_name: Option<String>,
    #[sqlx(rename = "TianGanDiZhiYear")]
    pub tian_gan_di_zhi_year: Option<String>,
    #[sqlx(rename = "TianGanDiZhiMonth")]
    pub tian_gan_di_zhi_month: Option<String>,
    #[sqlx(rename = "TianGanDiZhiDay")]
    pub tian_gan_di_zhi_day: Option<String>,
    #[sqlx(rename = "WuxingNaDay")]
    pub wuxing_na_day: Option<String>,
    #[sqlx(rename = "JianShen")]
    pub jian_shen: Option<String>,
    #[sqlx(rename = "Chong")]
    pub chong: Option<String>,
    #[sqlx(rename = "SuiSha")]
    pub sui_sha: Option<String>,
}
