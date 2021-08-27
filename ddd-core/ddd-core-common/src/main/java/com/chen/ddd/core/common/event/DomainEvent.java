package com.chen.ddd.core.common.event;

import java.time.Instant;

/**
 * 领域事件
 *
 * @author cl
 * @version 1.0
 * @since 2020/11/24 11:27
 */
public interface DomainEvent {
    /**
     * 事件标识
     *
     * @return 标识
     */
    default String code() {
        return "";
    }

    /**
     * 事件类型
     *
     * @return 类型
     */
    default String type() {
        return this.getClass().getName();
    }

    /**
     * 事件发生时间
     *
     * @return 时间
     */
    default Instant occurredAt() {
        return Instant.now();
    }
}
