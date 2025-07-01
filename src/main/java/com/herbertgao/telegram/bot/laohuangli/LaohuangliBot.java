package com.herbertgao.telegram.bot.laohuangli;

import com.herbertgao.telegram.bot.laohuangli.business.service.LaohuangliBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.message.Message;
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
public class LaohuangliBot implements LongPollingSingleThreadUpdateConsumer {

    private final String botUsername;
    private final String botToken;

    @Autowired
    private LaohuangliBotService laohuangliBotService;

    public LaohuangliBot(@Value("${telegram.bot.username}") String botUsername,
                         @Value("${telegram.bot.token}") String botToken) {
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public void consume(Update update) {
        log.debug(update.toString());

        if (update.hasInlineQuery()) {
            InlineQuery inlineQuery = update.getInlineQuery();
            laohuangliBotService.inlineQuery(inlineQuery);
        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            laohuangliBotService.message(message);
        }
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}
