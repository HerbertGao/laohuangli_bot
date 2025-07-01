package com.herbertgao.telegram.bot.laohuangli.business.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * 替代Hutool的LocalDateTimeUtil
 *
 * @author HerbertGao
 * @date 2024-01-01
 */
public class DateUtil {

    /**
     * 纯日期格式 yyyyMMdd
     */
    public static final String PURE_DATE_PATTERN = "yyyyMMdd";
    
    /**
     * 标准日期格式 yyyy-MM-dd
     */
    public static final String NORMAL_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 格式化日期为纯日期格式 (yyyyMMdd)
     *
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String formatPureDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(PURE_DATE_PATTERN));
    }

    /**
     * 格式化日期为标准格式 (yyyy-MM-dd)
     *
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String formatNormal(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(NORMAL_DATE_PATTERN));
    }

    /**
     * 格式化日期时间
     *
     * @param dateTime 日期时间
     * @param pattern 格式模式
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化日期
     *
     * @param date 日期
     * @param pattern 格式模式
     * @return 格式化后的字符串
     */
    public static String format(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
} 