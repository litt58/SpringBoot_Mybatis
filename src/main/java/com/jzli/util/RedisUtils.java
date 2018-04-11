package com.jzli.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/11
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
@Component
public class RedisUtils {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

//    @Autowired
//    private Jedis jedis;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return 是否获取成功
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        String result = jedisPool.getResource().set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    public void set(String key, String value) {
        jedisPool.getResource().set(key, value);
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey
     * @param requestId
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedisPool.getResource().eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
