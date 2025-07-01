package com.herbertgao.telegram.bot.laohuangli.business.common.util;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * 随机数工具类
 * 基于Apache Commons Lang3
 *
 * @author HerbertGao
 * @date 2024-01-01
 */
public class RandomUtil {

    /**
     * 生成[min, max)区间的随机整数
     */
    public static int nextInt(int min, int max) {
        return RandomUtils.nextInt(min, max);
    }

    /**
     * 生成指定长度的随机数字字符串
     */
    public static String randomNumeric(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 生成指定长度的随机字母数字字符串
     */
    public static String randomAlphanumeric(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    /**
     * 创建基于种子的随机数生成器
     * 使用Apache Commons Lang3的RandomUtils
     *
     * @param seed 种子字节数组
     * @return Random实例
     */
    public static Random createSecureRandom(byte[] seed) {
        // 将字节数组转换为长整型种子
        long seedValue = 0;
        for (int i = 0; i < Math.min(seed.length, 8); i++) {
            seedValue = (seedValue << 8) | (seed[i] & 0xFF);
        }
        return new Random(seedValue);
    }

    /**
     * 创建基于种子的随机数生成器
     *
     * @param seed 种子字符串
     * @return Random实例
     */
    public static Random createSecureRandom(String seed) {
        return createSecureRandom(seed.getBytes());
    }
} 