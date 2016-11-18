package com.jzli.conf;

import org.msgpack.MessagePack;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

/**
 * =======================================================
 *
 * @Company 金色家网络科技有限公司-云存储业务部
 * @Date ：2016/11/9
 * @Author ：li.jinzhao
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class MsgPackRedisSerializer<T> implements RedisSerializer<T> {
    private MessagePack msgPack = new MessagePack();

    public byte[] serialize(T t) throws SerializationException {
        if (t == null)
            return new byte[0];
        try {
            return msgPack.write(t);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public T deserialize(byte[] bytes) throws SerializationException {
        try {
            T t = (T) msgPack.read(bytes);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
