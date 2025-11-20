use chrono::{NaiveDate, Local};

/// 日期工具函数
pub fn format_normal(date: &NaiveDate) -> String {
    date.format("%Y-%m-%d").to_string()
}

pub fn format_pure_date(date: &NaiveDate) -> String {
    date.format("%Y%m%d").to_string()
}

pub fn today() -> NaiveDate {
    Local::now().date_naive()
}
