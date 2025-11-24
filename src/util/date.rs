use chrono::{NaiveDate, Utc};
use chrono_tz::Asia::Shanghai;

/// 日期工具函数
pub fn format_normal(date: &NaiveDate) -> String {
    date.format("%Y-%m-%d").to_string()
}

pub fn format_pure_date(date: &NaiveDate) -> String {
    date.format("%Y%m%d").to_string()
}

/// 获取中国时区的今天日期
/// 使用固定的中国时区（UTC+8），不依赖系统时区设置
pub fn today() -> NaiveDate {
    Utc::now().with_timezone(&Shanghai).date_naive()
}
