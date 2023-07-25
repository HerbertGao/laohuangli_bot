package com.herbertgao.telegram.bot.laohuangli.business.common.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @program: laohuangli_bot
 * @description: SerilizeUtil
 * @author: HerbertGao
 * @create: 2020/11/29
 **/
public class SerializeUtil implements RedisSerializer<Object> {

    private final Charset charset;

    public SerializeUtil() {
        this(StandardCharsets.UTF_8);
    }

    public SerializeUtil(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return JSON.toJSONString(o).getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return JSONObject.parseObject(new String(bytes, charset));
    }
}
