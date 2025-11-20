use std::sync::Arc;
use teloxide::types::{InlineQuery, InlineQueryResult, InlineQueryResultArticle, InputMessageContent};
use crate::service::LaohuangliService;
use uuid::Uuid;

/// 内联查询服务
pub struct InlineQueryService {
    laohuangli_service: Arc<LaohuangliService>,
}

impl InlineQueryService {
    pub fn new(laohuangli_service: Arc<LaohuangliService>) -> Self {
        Self {
            laohuangli_service,
        }
    }

    /// 处理内联查询
    pub async fn answer_inline_query(
        &self,
        inline_query: &InlineQuery,
    ) -> anyhow::Result<Vec<InlineQueryResult>> {
        let user = &inline_query.from;
        let mut results = Vec::new();

        // 今日老黄历
        match self.laohuangli_service.get_log().await {
            Ok(log_content) => {
                let article = InlineQueryResultArticle {
                    id: Uuid::new_v4().to_string(),
                    title: "今日青年老黄历".to_string(),
                    input_message_content: InputMessageContent::Text(
                        teloxide::types::InputMessageContentText {
                            message_text: log_content,
                            parse_mode: Some(teloxide::types::ParseMode::Html),
                            entities: None,
                            link_preview_options: None,
                        },
                    ),
                    reply_markup: None,
                    url: None,
                    description: None,
                    thumbnail_url: None,
                    thumbnail_width: None,
                    thumbnail_height: None,
                };
                results.push(InlineQueryResult::Article(article));
            }
            Err(e) => {
                tracing::error!("获取今日老黄历失败: {}", e);
            }
        }

        // 个人老黄历
        match self.laohuangli_service.get_personal_log(user).await {
            Ok(personal_log) => {
                let article = InlineQueryResultArticle {
                    id: Uuid::new_v4().to_string(),
                    title: "今日我的老黄历".to_string(),
                    input_message_content: InputMessageContent::Text(
                        teloxide::types::InputMessageContentText {
                            message_text: personal_log,
                            parse_mode: Some(teloxide::types::ParseMode::Html),
                            entities: None,
                            link_preview_options: None,
                        },
                    ),
                    reply_markup: None,
                    url: None,
                    description: None,
                    thumbnail_url: None,
                    thumbnail_width: None,
                    thumbnail_height: None,
                };
                results.push(InlineQueryResult::Article(article));
            }
            Err(e) => {
                tracing::error!("获取个人老黄历失败: {}", e);
            }
        }

        Ok(results)
    }
}
