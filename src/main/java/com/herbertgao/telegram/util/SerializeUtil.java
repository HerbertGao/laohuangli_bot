package com.herbertgao.telegram.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        return JSONObject.parse(new String(bytes, charset));
    }
}
