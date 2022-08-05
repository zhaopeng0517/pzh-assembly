package p.zh.assembly.middleware.common.cache.realize;

import org.springframework.data.redis.core.RedisTemplate;
import p.zh.assembly.middleware.common.cache.standard.MiddlewareCache;

import java.util.concurrent.TimeUnit;

/**
 * 缓存操作实现
 *
 * @author 赵鹏
 * @date 2022/8/5
 */
public class SimpleMiddlewareCache implements MiddlewareCache {

    private final RedisTemplate<String, String> redisTemplate;

    public SimpleMiddlewareCache(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取val
     *
     * @param key
     * @return
     */
    @Override
    public String getValue(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置val
     *
     * @param key
     * @param data
     */
    @Override
    public void setValue(String key, String data) {
        this.redisTemplate.opsForValue().set(key , data);
    }

    /**
     * 设置指定时效的val
     *
     * @param key
     * @param data
     * @param time
     * @param timeUnit
     */
    @Override
    public void setValue(String key, String data, long time, TimeUnit timeUnit) {
        this.redisTemplate.opsForValue().set(key , data , time , timeUnit);
    }

    /**
     * 设置键的过期时间
     *
     * @param key
     * @param time
     * @param tu
     */
    @Override
    public void expireKey(String key, long time, TimeUnit tu) {
        this.redisTemplate.expire(key, time, tu);
    }
}
