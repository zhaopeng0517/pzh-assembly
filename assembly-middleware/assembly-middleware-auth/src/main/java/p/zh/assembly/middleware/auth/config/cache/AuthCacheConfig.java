package p.zh.assembly.middleware.auth.config.cache;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import p.zh.assembly.middleware.common.cache.realize.SimpleMiddlewareCache;
import p.zh.assembly.middleware.common.cache.standard.MiddlewareCache;

import javax.annotation.Resource;

/**
 * 缓存配置
 *
 * @author 赵鹏
 * @date 2022/8/5
 */
@SpringBootConfiguration
public class AuthCacheConfig {

    @Resource
    private RedisTemplate<String , String> redisTemplate;

    @Bean
    public MiddlewareCache cache(RedisTemplate<String , String> redisTemplate) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return new SimpleMiddlewareCache(redisTemplate);
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
