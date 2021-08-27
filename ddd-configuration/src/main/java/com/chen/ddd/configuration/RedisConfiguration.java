package com.chen.ddd.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/5
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisLockRegistry lockRegistry(RedisConnectionFactory connectionFactory) {
        return new RedisLockRegistry(connectionFactory, "ddd-lock");
    }

}
