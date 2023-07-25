package com.herbertgao.telegram.bot.laohuangli.business.service;

import com.herbertgao.telegram.bot.laohuangli.business.common.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Inline消息服务
 *
 * @author HerbertGao
 * @date 2021-12-10
 */
@Slf4j
@Service
public class InlineQueryService {

    @Autowired
    private LaohuangliService laohuangliService;

    public AnswerInlineQuery answerInlineQuery(InlineQuery inlineQuery) {
        List<InlineQueryResult> resultList = new ArrayList<>();
        User user = inlineQuery.getFrom();
        String query = inlineQuery.getQuery();

        InlineQueryResultArticle r = new InlineQueryResultArticle();
        r.setId(IdUtil.getId().toString());
        r.setTitle("今日青年老黄历");
        r.setInputMessageContent(new InputTextMessageContent() {
            {
                setMessageText(laohuangliService.getLog());
            }
        });
        resultList.add(r);

        InlineQueryResultArticle r1 = new InlineQueryResultArticle();
        r1.setId(IdUtil.getId().toString());
        r1.setTitle("今日我的老黄历");
        r1.setInputMessageContent(new InputTextMessageContent() {
            {
                setMessageText(laohuangliService.getPersonalLog(user));
            }
        });
        resultList.add(r1);

        String inlineQueryId = inlineQuery.getId();

        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
        answerInlineQuery.setInlineQueryId(inlineQueryId);
        answerInlineQuery.setResults(resultList);
        answerInlineQuery.setCacheTime(0);
        return answerInlineQuery;
    }
}
