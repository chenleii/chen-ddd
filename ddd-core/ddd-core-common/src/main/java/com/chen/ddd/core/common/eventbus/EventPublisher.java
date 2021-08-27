package com.chen.ddd.core.common.eventbus;

import com.chen.ddd.core.common.event.DomainEvent;

/**
 * 事件发布器
 *
 * @author cl
 * @version 1.0
 * @since 2020/10/30
 */
public interface EventPublisher {

    /**
     * 发布领域事件
     *
     * @param event 事件
     */
    void publish(DomainEvent event);
}

