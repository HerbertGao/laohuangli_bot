use teloxide::prelude::*;
use teloxide::types::Message;
use teloxide::utils::command::BotCommands;
use teloxide::sugar::request::RequestReplyExt;
use crate::service::{MessageService, InlineQueryService};
use crate::bot::Command;

/// Bot 处理器
pub struct BotHandler {
    message_service: MessageService,
    inline_query_service: InlineQueryService,
}

impl BotHandler {
    pub fn new(message_service: MessageService, inline_query_service: InlineQueryService) -> Self {
        Self {
            message_service,
            inline_query_service,
        }
    }

    /// 处理消息
    pub async fn handle_message(&self, bot: Bot, msg: Message) -> ResponseResult<()> {
        if let Some(text) = msg.text() {
            if let Ok(command) = Command::parse(text, "") {
                let response = match command {
                    Command::Laohuangli => {
                        self.message_service.get_laohuangli().await
                            .unwrap_or_else(|e| {
                                tracing::error!("获取老黄历失败: {}", e);
                                "获取老黄历失败，请稍后再试".to_string()
                            })
                    }
                    Command::Myhuangli => {
                        if let Some(user) = &msg.from {
                            self.message_service.get_my_huangli(user).await
                                .unwrap_or_else(|e| {
                                    tracing::error!("获取个人老黄历失败: {}", e);
                                    "获取个人老黄历失败，请稍后再试".to_string()
                                })
                        } else {
                            "无法获取用户信息".to_string()
                        }
                    }
                };

                bot.send_message(msg.chat.id, response)
                    .parse_mode(teloxide::types::ParseMode::Html)
                    .reply_to(msg.id)
                    .await?;
            }
        }

        Ok(())
    }

    /// 处理内联查询
    pub async fn handle_inline_query(&self, bot: Bot, query: teloxide::types::InlineQuery) -> ResponseResult<()> {
        match self.inline_query_service.answer_inline_query(&query).await {
            Ok(results) => {
                bot.answer_inline_query(query.id, results)
                    .cache_time(0)
                    .await?;
            }
            Err(e) => {
                tracing::error!("处理内联查询失败: {}", e);
            }
        }

        Ok(())
    }
}
