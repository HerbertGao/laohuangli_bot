package com.herbertgao.telegram.bot.laohuangli.business.service;

import com.herbertgao.telegram.bot.laohuangli.business.common.util.DateUtil;
import com.herbertgao.telegram.bot.laohuangli.business.common.util.RandomUtil;
import com.herbertgao.telegram.bot.laohuangli.business.common.util.SecureUtil;
import com.herbertgao.telegram.bot.laohuangli.business.common.constant.Config;
import com.herbertgao.telegram.bot.laohuangli.business.common.util.TelegramBotUtil;
import com.herbertgao.telegram.bot.laohuangli.database.entity.Log;
import com.herbertgao.telegram.bot.laohuangli.database.entity.Lunar;
import com.herbertgao.telegram.bot.laohuangli.database.entity.Yiji;
import com.herbertgao.telegram.bot.laohuangli.database.service.LogService;
import com.herbertgao.telegram.bot.laohuangli.database.service.LunarService;
import com.herbertgao.telegram.bot.laohuangli.database.service.YijiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;
import java.util.List;

/**
 * 老黄历服务
 *
 * @author HerbertGao
 * @date 2020-11-29
 */
@Slf4j
@Service
public class LaohuangliService {

    private static final String[] CHINESE_NUMBER = {"一", "二", "三", "四", "五", "六", "日"};

    @Autowired
    private RedisService redisService;

    @Autowired
    private LogService logService;
    @Autowired
    private LunarService lunarService;
    @Autowired
    private YijiService yijiService;

    /**
     * 获取老黄历
     *
     * @return {@link String}
     */
    public String getLog() {
        LocalDate today = LocalDate.now();
        return getLog(today);
    }

    /**
     * 获取老黄历
     *
     * @param date 日期
     * @return {@link String}
     */
    public String getLog(LocalDate date) {
        Log log = redisService.getLogByDate(date);
        if (log != null) {
            return log.getContent();
        } else {
            return logService.getLogContentByDate(date);
        }
    }

    /**
     * 生成老黄历
     *
     * @param date 日期
     * @return {@link Log}
     */
    public Log geneLog(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        Lunar lunar = lunarService.getLunarByDate(date);
        if (lunar != null) {
            sb.append(date.getYear()).append("年")
                    .append(date.getMonthValue()).append("月")
                    .append(date.getDayOfMonth()).append("日")
                    .append("，")
                    .append("星期").append(CHINESE_NUMBER[date.getDayOfWeek().getValue() - 1])
                    .append("，")
                    .append("农历").append(lunar.getLmonth()).append(lunar.getLday());
            if (StringUtils.isNotBlank(lunar.getSolartermname())) {
                sb.append("，")
                        .append(lunar.getSolartermname());
            }
            sb.append("。\n")
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

        String randomSeed = DateUtil.formatPureDate(date)
                + Config.token.replace(":", "");
        randomSeed = SecureUtil.md5(randomSeed);
        int seed = RandomUtil.createSecureRandom(randomSeed.getBytes()).nextInt();
        List<Yiji> yijiList = yijiService.getDailyYijiRandom(seed);
        sb.append("宜").append(yijiList.get(0).getYi()).append("，")
                .append("宜").append(yijiList.get(1).getYi()).append("，")
                .append("宜").append(yijiList.get(2).getYi()).append("，")
                .append("宜").append(yijiList.get(3).getYi()).append("，")
                .append("宜交配；")
                .append("忌").append(yijiList.get(4).getJi()).append("，")
                .append("忌").append(yijiList.get(5).getJi()).append("。");
        String result = sb.toString();

        Log log = logService.insertLog(date, result);
        redisService.setLogByDate(log, date);
        return log;
    }


    /**
     * 得到个人黄历
     *
     * @param user 用户
     * @return {@link String}
     */
    public String getPersonalLog(User user) {
        LocalDate today = LocalDate.now();
        String personalLog = redisService.getPersonalLog(today, user);
        if (StringUtils.isNotBlank(personalLog)) {
            return personalLog;
        } else {
            return genePersonalLog(user);
        }
    }

    /**
     * 生成个人黄历
     *
     * @param user 用户
     * @return {@link String}
     */
    public String genePersonalLog(User user) {
        Long id = user.getId();
        String userFullName = TelegramBotUtil.getUserFullName(user);
        String prefix = userFullName + " 今日：";

        LocalDate today = LocalDate.now();
        String randomSeed = DateUtil.formatPureDate(today)
                + id
                + Config.token.replace(":", "");
        randomSeed = SecureUtil.md5(randomSeed);
        int seed = RandomUtil.createSecureRandom(randomSeed.getBytes()).nextInt();
        List<Yiji> yijiList = yijiService.getYijiRandom(seed, 2);
        String result = prefix + "宜" + yijiList.get(0).getYi() + "，" + "忌" + yijiList.get(1).getJi() + "。";

        redisService.setPersonalLog(today, user, result);
        return result;
    }

}
