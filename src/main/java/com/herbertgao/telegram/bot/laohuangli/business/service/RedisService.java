package com.herbertgao.telegram.bot.laohuangli.business.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson2.JSON;
import com.herbertgao.telegram.bot.laohuangli.business.common.constant.RedisConstant;
import com.herbertgao.telegram.bot.laohuangli.business.common.util.RedisUtil;
import com.herbertgao.telegram.bot.laohuangli.database.entity.Log;
import com.herbertgao.telegram.bot.laohuangli.database.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;
import java.util.Set;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private LogService logService;

    public Log getLogByDate(LocalDate date) {
        String redisKey = RedisConstant.LOG + LocalDateTimeUtil.formatNormal(date);
        if (RedisUtil.hasKey(redisKey)) {
            return JSON.parseObject(RedisUtil.get(redisKey), Log.class);
        } else {
            return logService.getLogByDate(date);
        }
    }

    public void setLogByDate(Log log, LocalDate date) {
        String redisKey = RedisConstant.LOG + LocalDateTimeUtil.formatNormal(date);
        RedisUtil.set(redisKey, JSON.toJSONString(log));
    }

    public String getPersonalLog(LocalDate date, User user) {
        String redisKey = RedisConstant.PERSONAL_LOG + LocalDateTimeUtil.formatNormal(date) + ":" + user.getId();
        if (RedisUtil.hasKey(redisKey)) {
            return RedisUtil.get(redisKey);
        } else {
            return null;
        }
    }

    public void setPersonalLog(LocalDate date, User user, String logContent) {
        String redisKey = RedisConstant.PERSONAL_LOG + LocalDateTimeUtil.formatNormal(date) + ":" + user.getId();
        RedisUtil.set(redisKey, logContent);
    }

    public void clearKeys(String redisKey) {
        Set<String> keys = RedisUtil.keys(redisKey + "*");
        if (CollectionUtils.isNotEmpty(keys)) {
            RedisUtil.delete(keys);
        }
        log.info("清理了{}个缓存信息", keys.size());
    }

}
