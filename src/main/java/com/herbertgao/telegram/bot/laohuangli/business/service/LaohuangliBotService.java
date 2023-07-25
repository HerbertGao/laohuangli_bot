package com.herbertgao.telegram.bot.laohuangli.business.service;


import com.herbertgao.telegram.bot.laohuangli.business.common.constant.Command;
import com.herbertgao.telegram.bot.laohuangli.business.common.util.TelegramBotUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * 我的机器人服务
 *
 * @author HerbertGao
 * @date 2019-06-08
 */
@Slf4j
@Service
public class LaohuangliBotService {

    @Lazy
    @Autowired
    private AbsSender absSender;

    @Autowired
    private InlineQueryService inlineQueryService;
    @Autowired
    private MessageService messageService;

    /**
     * Inline Query
     *
     * @param inlineQuery 内联查询
     */
    public void inlineQuery(InlineQuery inlineQuery) {
        log.debug(inlineQuery.toString());

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
     * @param message 消息
     */
    public void message(Message message) {
        log.debug(message.toString());

        String chatId = message.getChatId().toString();
        User user = message.getFrom();

        if (message.hasText()) {
            String text = TelegramBotUtil.getTextByMessage(message, null);

            if (message.isCommand()) {

                String msg = "";
                if (TelegramBotUtil.isMatchCommand(text, Command.LAOHUANGLI_COMMAND)) {
                    msg = messageService.getLaohuangli();
                } else if (TelegramBotUtil.isMatchCommand(text, Command.MY_COMMAND)) {
                    msg = messageService.getMyHuangli(user);
                }

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
