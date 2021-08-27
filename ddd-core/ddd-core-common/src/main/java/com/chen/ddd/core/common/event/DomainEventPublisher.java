package com.chen.ddd.core.common.event;

import com.chen.ddd.core.common.eventbus.EventBus;

/**
 * 领域事件发布者
 * <p>
 * 使领域对象具有发布事件能力
 *
 * @author cl
 * @version 1.0
 * @since 2021/6/21 17:24
 */
public interface DomainEventPublisher {

    /**
     * 发布领域事件
     *
     * @param domainEvent domainEvent
     */
    default <T extends DomainEvent> void publishEvent(T domainEvent) {
        EventBus.publish(domainEvent);
    }
}
