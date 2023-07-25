package com.herbertgao.telegram.bot.laohuangli;

import com.herbertgao.telegram.bot.laohuangli.business.service.LaohuangliBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;

/**
 * laohuangli_bot
 *
 * @author HerbertGao
 * @date 2023-06-07
 */
@Slf4j
@Component
public class LaohuangliBot extends TelegramLongPollingBot {

    private final String botUsername;

    @Autowired
    private LaohuangliBotService laohuangliBotService;

    public LaohuangliBot(@Value("${telegram.bot.username}") String botUsername,
                         @Value("${telegram.bot.token}") String botToken) {
        super(botToken);
        this.botUsername = botUsername;
    }


    @Override
    public void onUpdateReceived(Update update) {
        log.debug(update.toString());

        if (update.hasInlineQuery()) {
            InlineQuery inlineQuery = update.getInlineQuery();
            laohuangliBotService.inlineQuery(inlineQuery);
        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            laohuangliBotService.message(message);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
