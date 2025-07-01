package com.herbertgao.telegram.bot;

import com.herbertgao.telegram.bot.laohuangli.LaohuangliBot;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class BotRegister {

    @Autowired
    LaohuangliBot laohuangliBot;

    @PostConstruct
    public void init() {
        try {
            TelegramBotsLongPollingApplication telegramBotsApi = new TelegramBotsLongPollingApplication();
            telegramBotsApi.registerBot(laohuangliBot.getBotToken(), laohuangliBot);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

}
