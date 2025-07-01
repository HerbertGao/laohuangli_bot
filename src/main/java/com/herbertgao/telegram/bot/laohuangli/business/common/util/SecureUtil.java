package com.herbertgao.telegram.bot.laohuangli.business.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 安全工具类
 * 基于Apache Commons Codec
 *
 * @author HerbertGao
 * @date 2024-01-01
 */
public class SecureUtil {

    /**
     * MD5加密
     *
     * @param data 要加密的数据
     * @return MD5加密后的字符串
     */
    public static String md5(String data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * MD5加密
     *
     * @param data 要加密的数据字节数组
     * @return MD5加密后的字符串
     */
    public static String md5(byte[] data) {
        return DigestUtils.md5Hex(data);
    }
} 