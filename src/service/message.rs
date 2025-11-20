use std::sync::Arc;
use teloxide::types::User;
use crate::service::LaohuangliService;

/// 消息服务
pub struct MessageService {
    laohuangli_service: Arc<LaohuangliService>,
}

impl MessageService {
    pub fn new(laohuangli_service: Arc<LaohuangliService>) -> Self {
        Self {
            laohuangli_service,
        }
    }

    /// 获取老黄历
    pub async fn get_laohuangli(&self) -> anyhow::Result<String> {
        self.laohuangli_service.get_log().await
    }

    /// 获取个人老黄历
    pub async fn get_my_huangli(&self, user: &User) -> anyhow::Result<String> {
        self.laohuangli_service.get_personal_log(user).await
    }
}
