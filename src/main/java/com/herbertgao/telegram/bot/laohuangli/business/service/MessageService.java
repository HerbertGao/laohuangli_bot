package com.herbertgao.telegram.bot.laohuangli.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * 消息服务
 *
 * @author HerbertGao
 * @date 2019-06-09
 */
@Service
public class MessageService {
    @Autowired
    private LaohuangliService laohuangliService;

    public String getLaohuangli() {
        return laohuangliService.getLog();
    }

    public String getMyHuangli(User user) {
        return laohuangliService.getPersonalLog(user);
    }
}
