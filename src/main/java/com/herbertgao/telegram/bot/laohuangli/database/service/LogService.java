package com.herbertgao.telegram.bot.laohuangli.database.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.herbertgao.telegram.bot.laohuangli.business.service.LaohuangliService;
import com.herbertgao.telegram.bot.laohuangli.database.entity.Log;
import com.herbertgao.telegram.bot.laohuangli.database.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LogService extends ServiceImpl<LogMapper, Log> {

    @Lazy
    @Autowired
    private LaohuangliService laohuangliService;

    /**
     * 根据日期获取日志
     *
     * @param date 日期
     * @return {@link Log}
     */
    public Log getLogByDate(LocalDate date) {
        QueryWrapper<Log> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Log::getDate, date);
        Log log = this.getOne(wrapper);
        if (log != null) {
            return log;
        } else {
            return laohuangliService.geneLog(date);
        }
    }

    /**
     * 根据日期获取日志内容
     *
     * @param date 日期
     * @return {@link String}
     */
    public String getLogContentByDate(LocalDate date) {
        Log log = this.getLogByDate(date);
        return log.getContent();
    }

    /**
     * 插入日志
     *
     * @param date    日期
     * @param content 内容
     * @return {@link Log}
     */
    public Log insertLog(LocalDate date, String content) {
        Log log = new Log();
        log.setDate(date);
        log.setContent(content);
        this.save(log);
        return getLogByDate(date);
    }

}
