package com.chen.ddd.interfaces.http.handle;

import java.lang.annotation.*;

/**
 * 包装返回结果注解
 *
 * @author cl
 * @version 1.0
 * @since 2020/12/1 14:12
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultWrapper {
}
