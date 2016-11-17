package com.jzli.conf;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * =======================================================
 *
 * @Company 金色家网络科技有限公司-云存储业务部
 * @Date ：2016/11/8
 * @Author ：li.jinzhao
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
@Configuration
@EnableCaching
public class RedisCacheConfiguration extends CachingConfigurerSupport {

    private final static String CACHE_KEY_PREFIX = "jycloudst";

    /**
     * 缓存管理器.
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(60 * 5);
        return redisCacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                if (params.length == 0) {
                    return CACHE_KEY_PREFIX + method.getName();
                }
                if (params.length == 1) {
                    Object param = params[0];
                    if (param != null && !param.getClass().isArray()) {
                        return CACHE_KEY_PREFIX + method.getName() + "_" + param;
                    }
                }
                return CACHE_KEY_PREFIX + method.getName() + " [" + StringUtils.arrayToCommaDelimitedString(params) + "]";
            }
        };
    }

    /**
     * redis模板操作类,类似于jdbcTemplate的一个类;
     * <p>
     * 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
     * <p>
     * 这里在扩展下：RedisTemplate这个类不见得很好操作，我们可以在进行扩展一个我们
     * <p>
     * 自己的缓存类，比如：RedisStorage类;
     *
     * @param factory : 通过Spring进行注入，参数在application.properties进行配置；
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        redisTemplate.setValueSerializer(new RedisSerializer<Object>() {
            @Override
            public byte[] serialize(Object object) throws SerializationException {
                if (object == null) {
                    return new byte[0];
                }
                if (!(object instanceof Serializable)) {
                    throw new IllegalArgumentException("RedisSerializer.serialize requires a Serializable payload "
                            + "but received an object of type [" + object.getClass().getName() + "]");
                }
                return SerializationUtils.serialize((Serializable) object);
            }

            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                if (bytes == null || bytes.length == 0) {
                    return null;
                }
                return SerializationUtils.deserialize(bytes);
            }
        });

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
