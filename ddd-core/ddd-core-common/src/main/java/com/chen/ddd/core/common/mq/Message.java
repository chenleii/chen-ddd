package com.chen.ddd.core.common.mq;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/22 12:15
 */
public interface Message {

    default String getKey() {
        return "";
    }
}
