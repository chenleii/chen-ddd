package com.chen.ddd.test.configuration.redis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.SocketUtils;
import redis.embedded.RedisServer;

import java.util.Objects;

/**
 * Embedded Redis Configuration.
 *
 * @author chen
 * @since 2020/9/3 0:37.
 */
public class EnableEmbeddedRedisConfiguration {

    @Bean
    public BeanPostProcessor embeddedRedisPropertiesBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof RedisProperties) {
                    RedisProperties redisProperties = (RedisProperties) bean;
                    redisProperties.setHost("localhost");
                    redisProperties.setPort(SocketUtils.findAvailableTcpPort());
                    redisProperties.setPassword(null);
                }
                return bean;
            }
        };
    }

    @Bean
    public RedisServerBean redisServerBean(RedisProperties redisProperties) {
        return new RedisServerBean(redisProperties);
    }

    static class RedisServerBean implements InitializingBean, DisposableBean {
        private RedisServer redisServer;

        public RedisServerBean(RedisProperties redisProperties) {
            this.redisServer = RedisServer.builder()
                    .bind(redisProperties.getHost())
                    .port(redisProperties.getPort())
                    .build();
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            redisServer.start();
        }

        @Override
        public void destroy() throws Exception {
            if (Objects.nonNull(redisServer)) {
                redisServer.stop();
            }
        }
    }
}
