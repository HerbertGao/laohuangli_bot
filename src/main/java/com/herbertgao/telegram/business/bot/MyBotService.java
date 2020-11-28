package com.herbertgao.telegram.business.bot;

import com.herbertgao.telegram.bot.Command;
import com.herbertgao.telegram.util.TelegramBotUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @program: laohuangli_bot
 * @description: MyBotService
 * @author: HerbertGao
 * @create: 2020/11/28
 **/
@Service
public class MyBotService {

    private final static Logger logger = LoggerFactory.getLogger(MyBotService.class);

    @Autowired
    private AbsSender absSender;

    @Autowired
    private InlineQueryService inlineQueryService;
    @Autowired
    private MessageService messageService;

    /**
     * Inline Query
     *
     * @param inlineQuery
     */
    public void inlineQuery(InlineQuery inlineQuery) {
        logger.debug(inlineQuery.toString());

        try {
            AnswerInlineQuery results = inlineQueryService.answerInlineQuery(inlineQuery);
            absSender.execute(results);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message
     *
     * @param message
     */
    public void message(Message message) {
        logger.debug(message.toString());

        String chatId = message.getChatId().toString();
        User user = message.getFrom();

        if (message.hasText()) {
            String text = TelegramBotUtil.getTextByMessage(message, null);

            if (message.isCommand()) {

                if (TelegramBotUtil.isMatchCommand(text, Command.LAOHUANGLI_COMMAND)) {
                    String msg = messageService.getLaohuangli();
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText(msg);
                    sendMessage.enableHtml(true);
                    sendMessage.setReplyToMessageId(message.getMessageId());

                    try {
                        absSender.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
