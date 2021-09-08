package com.chen.ddd.configuration.test.redis;

import java.lang.annotation.*;

/**
 *
 * @author cl
 * @since 2020/9/9 17:36
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableEmbeddedRedis
public @interface AutoConfigureTestRedis {
}
