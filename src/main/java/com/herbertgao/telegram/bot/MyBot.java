package com.herbertgao.telegram.bot;

import com.herbertgao.telegram.business.bot.MyBotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;

/**
 * @program: laohuangli_bot
 * @description: MyBot
 * @author: HerbertGao
 * @create: 2020/11/28
 **/
@Component
public class MyBot extends TelegramLongPollingBot {

    private final static Logger logger = LoggerFactory.getLogger(MyBot.class);

    @Autowired
    private MyBotService myBotService;

    @Override
    public String getBotUsername() {
        return Config.getUsername();
    }

    @Override
    public String getBotToken() {
        return Config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.debug(update.toString());

        if (update.hasInlineQuery()) {
            InlineQuery inlineQuery = update.getInlineQuery();
            myBotService.inlineQuery(inlineQuery);
        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            myBotService.message(message);
        }
    }
}
