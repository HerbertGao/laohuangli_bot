package com.herbertgao.telegram.bot.laohuangli.business.service;


import com.herbertgao.telegram.bot.laohuangli.business.common.constant.Command;
import com.herbertgao.telegram.bot.laohuangli.business.common.util.TelegramBotUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

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
    private TelegramClient telegramClient;

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
            telegramClient.execute(results);
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

                if (msg != null && !msg.trim().isEmpty()) {
                    SendMessage sendMessage = SendMessage.builder()
                            .chatId(chatId)
                            .text(msg)
                            .parseMode("HTML")
                            .replyToMessageId(message.getMessageId())
                            .build();

                    try {
                        telegramClient.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        log.error("发送消息失败: {}", e.getMessage(), e);
                    }
                } else {
                    log.debug("忽略未定义的命令: {}", text);
                }
            }
        }
    }
}
