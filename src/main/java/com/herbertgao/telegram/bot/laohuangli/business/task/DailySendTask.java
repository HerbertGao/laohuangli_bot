package com.herbertgao.telegram.bot.laohuangli.business.task;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.herbertgao.telegram.bot.laohuangli.business.service.LaohuangliService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.time.LocalDate;

/**
 * 每日发送任务
 *
 * @author HerbertGao
 * @date 2020-11-29
 */
@Slf4j
@Component
public class DailySendTask {

    @Autowired
    private AbsSender absSender;
    @Value("${telegram.channel.id}")
    private String channelId;

    @Autowired
    private LaohuangliService laohuangliService;

    /**
     * 生成老黄历
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void geneLogTask() {
        log.info("开始生成老黄历日志");
        int generateDays = 14;

        LocalDate generateDate = LocalDate.now();
        int n = 1;
        while (n < generateDays) {
            log.info("生成中, 日期:{}", LocalDateTimeUtil.formatNormal(generateDate));
            laohuangliService.getLog(generateDate);
            generateDate = generateDate.plusDays(1);
            n++;
        }
        log.info("结束生成老黄历日志");
    }

    /**
     * 定时发送老黄历
     */
    @Scheduled(cron = "0 0 22 * * ?")
    @SneakyThrows
    public void dailySendTask() {

        LocalDate date = LocalDate.now().plusDays(1);

        String msg = laohuangliService.getLog(date);
        absSender.execute(new SendMessage(channelId, msg));

    }

    /**
     * 定时发送老黄历
     */
    @Scheduled(cron = "0 0 0 1 2 ?")
    @SneakyThrows
    public void dailySendTask1() {

        LocalDate date = LocalDate.now();

        String msg = laohuangliService.getLog(date);
        absSender.execute(new SendMessage(channelId, msg));

    }
}
