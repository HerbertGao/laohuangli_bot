package com.herbertgao.telegram.business.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.herbertgao.telegram.bot.Command;
import com.herbertgao.telegram.bot.Config;
import com.herbertgao.telegram.database.entity.Lunar;
import com.herbertgao.telegram.database.entity.Yiji;
import com.herbertgao.telegram.database.service.LogService;
import com.herbertgao.telegram.database.service.LunarService;
import com.herbertgao.telegram.database.service.YijiService;
import com.herbertgao.telegram.util.RedisUtil;
import com.herbertgao.telegram.util.TelegramBotUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;
import java.util.List;

/**
 * @program: laohuangli_bot
 * @description: LaohuangliService
 * @author: HerbertGao
 * @create: 2020/11/29
 **/
@Service
public class LaohuangliService {

    private String[] chineseNumber = {"一", "二", "三", "四", "五", "六", "日"};

    @Autowired
    private LogService logService;
    @Autowired
    private LunarService lunarService;
    @Autowired
    private YijiService yijiService;

    @Autowired
    private RedisUtil redis;

    public String getLaohuangli() {
        LocalDate today = LocalDate.now();
        return getLaohuangli(today);
    }

    public String getLaohuangli(LocalDate date) {
        String log = logService.getLogByDate(date);
        if (StringUtils.isNotBlank(log)) {
            return log;
        } else {
            StringBuilder sb = new StringBuilder();
            Lunar lunar = lunarService.getLunarByDate(date);
            if (lunar != null) {
                sb.append(date.getYear()).append("年")
                        .append(date.getMonthValue()).append("月")
                        .append(date.getDayOfMonth()).append("日")
                        .append("，")
                        .append("星期").append(chineseNumber[date.getDayOfWeek().getValue() - 1])
                        .append("，")
                        .append("农历").append(lunar.getLmonth()).append(lunar.getLday())
                        .append("。\n")
                        .append("干支：")
                        .append(lunar.getTiangandizhiyear()).append("年")
                        .append("，")
                        .append(lunar.getTiangandizhimonth()).append("月")
                        .append("，")
                        .append(lunar.getTiangandizhiday()).append("日")
                        .append("。\n")
                        .append("五行：")
                        .append(lunar.getWuxingnaday())
                        .append("，")
                        .append(lunar.getJianshen()).append("执位")
                        .append("。\n")
                        .append(lunar.getChong())
                        .append("，")
                        .append(lunar.getSuisha())
                        .append("。\n");
            }

            String randomSeed = LocalDateTimeUtil.format(date, DatePattern.PURE_DATE_PATTERN)
                    + Config.getToken().replace(":", "");
            List<Yiji> yijiList = yijiService.getDailyYijiRandom(randomSeed);
            sb.append("宜").append(yijiList.get(0).getYi()).append("，")
                    .append("宜").append(yijiList.get(1).getYi()).append("，")
                    .append("宜").append(yijiList.get(2).getYi()).append("，")
                    .append("宜").append(yijiList.get(3).getYi()).append("，")
                    .append("宜交配；")
                    .append("忌").append(yijiList.get(4).getJi()).append("，")
                    .append("忌").append(yijiList.get(5).getJi()).append("。");
            String result = sb.toString();

            logService.insertLog(date, result);

            return result;
        }
    }

    public String getPersonalHuangli(User user) {
        Integer id = user.getId();
        String userFullName = TelegramBotUtil.getUserFullName(user);
        String prefix = userFullName + " 今日：";

        LocalDate today = LocalDate.now();
        String myHuangliKey = Config.getUsername()
                + "_" + Command.MY_COMMAND
                + "_" + id
                + "_" + LocalDateTimeUtil.format(today, DatePattern.PURE_DATE_PATTERN);
        if (redis.hasKey(myHuangliKey)) {
            return prefix + redis.get(myHuangliKey).toString();
        } else {
            String randomSeed = LocalDateTimeUtil.format(today, DatePattern.PURE_DATE_PATTERN)
                    + id
                    + Config.getToken().replace(":", "");
            List<Yiji> yijiList = yijiService.getYijiRandom(randomSeed, 2);
            StringBuilder sb = new StringBuilder();
            sb.append("宜").append(yijiList.get(0).getYi()).append("，")
                    .append("忌").append(yijiList.get(1).getJi()).append("。");
            String result = sb.toString();

            redis.set(myHuangliKey, result, RedisUtil.DAYS);
            return prefix + result;
        }
    }
}
