package com.herbertgao.telegram.business.job.handler;

import com.herbertgao.telegram.business.service.LaohuangliService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @program: laohuangli_bot
 * @description: DailySendJobHandler
 * @author: HerbertGao
 * @create: 2020/11/29
 **/
@Component
public class DailySendJobHandler {

    @Autowired
    private AbsSender absSender;
    @Value("${telegram.channel.id}")
    private String channelId;

    @Autowired
    private LaohuangliService laohuangliService;

    /**
     * 生成老黄历
     *
     * @param param
     * @return
     */
    @XxlJob(value = "generateLaohuangliJobHandler")
    public ReturnT<String> generateLaohuangliJobHandler(String param) {

        String[] params = param.split(",");

        int generateDays = params.length > 0 ? Integer.parseInt(params[0]) : 14;
        LocalDate date = params.length > 1 ? LocalDate.parse(params[1], DateTimeFormatter.ofPattern("yyyyMMdd")) : null;

        try {
            if (date != null) {
                if (StringUtils.isNotBlank(laohuangliService.getLaohuangli(date))) {
                    return IJobHandler.SUCCESS;
                } else {
                    return IJobHandler.FAIL;
                }
            } else {
                LocalDate generateDate = LocalDate.now();
                int n = 1;
                while (n < generateDays) {
                    if (StringUtils.isBlank(laohuangliService.getLaohuangli(generateDate))) {
                        return IJobHandler.FAIL;
                    }
                    generateDate = generateDate.plusDays(1);
                    n++;
                }
                return IJobHandler.SUCCESS;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            return IJobHandler.FAIL;
        }
    }

    /**
     * 定时发送老黄历
     *
     * @param param
     * @return
     */
    @XxlJob(value = "dailySendJobHandler")
    public ReturnT<String> dailySendJobHandler(String param) {

        String[] params = param.split(",");

        LocalDate date = params.length > 0 ? LocalDate.parse(params[0], DateTimeFormatter.ofPattern("yyyyMMdd")) : null;

        try {
            if (date == null) {
                date = LocalDate.now().plusDays(1);
            }
            String msg = laohuangliService.getLaohuangli(date);

            absSender.execute(new SendMessage(channelId, msg));

            return IJobHandler.SUCCESS;
        } catch (RuntimeException | TelegramApiException re) {
            re.printStackTrace();
            return IJobHandler.FAIL;
        }

    }
}
