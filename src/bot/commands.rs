use teloxide::utils::command::BotCommands;

/// Bot 命令枚举
#[derive(BotCommands, Clone)]
#[command(rename_rule = "lowercase", description = "青年老黄历Bot命令：")]
pub enum Command {
    #[command(description = "获取今日老黄历")]
    Laohuangli,
    #[command(description = "获取个人老黄历")]
    Myhuangli,
}
