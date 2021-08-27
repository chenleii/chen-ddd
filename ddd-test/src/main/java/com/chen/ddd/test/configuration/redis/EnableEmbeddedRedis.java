package com.chen.ddd.test.configuration.redis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启嵌入式redis配置
 *
 * @author cl
 * @since 2020/9/9 18:13.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({EnableEmbeddedRedisConfiguration.class})
public @interface EnableEmbeddedRedis {
    
}