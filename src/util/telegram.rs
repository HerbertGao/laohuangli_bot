use teloxide::types::User;

/// Telegram 工具函数
pub fn get_user_full_name(user: &User) -> String {
    let first_name = &user.first_name;
    let last_name = user.last_name.as_deref().unwrap_or("");
    
    if last_name.is_empty() {
        first_name.clone()
    } else {
        format!("{} {}", first_name, last_name)
    }
}
