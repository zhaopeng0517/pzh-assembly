package p.zh.assembly.middleware.common.cache.standard;

import java.util.concurrent.TimeUnit;

/**
 * 缓存操作标准
 *
 * @author 赵鹏
 * @date 2022/8/5
 */
public interface MiddlewareCache {

    /**
     * 获取val
     * @param key
     * @return
     */
    String getValue(String key);

    /**
     * 设置val
     * @param key
     * @param data
     */
    void setValue(String key , String data);

    /**
     * 设置指定时效的val
     * @param key
     * @param data
     * @param time
     * @param timeUnit
     */
    void setValue(String key , String data , long time , TimeUnit timeUnit);


    /**
     * 设置键的过期时间
     * @param key
     * @param time
     * @param tu
     */
    void expireKey(String key , long time , TimeUnit tu);
}
