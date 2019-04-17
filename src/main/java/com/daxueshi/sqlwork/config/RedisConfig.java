package com.daxueshi.sqlwork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author onion
 * @date 2019-04-16 -21:46
 */
@Configuration
public class RedisConfig {
    /***
     * 重写序列化器
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate <Object, Object> jsonRedisTemplate
            (RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer(Object.class));
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
