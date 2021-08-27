package com.chen.ddd.core.common.eventbus;

import com.chen.ddd.core.common.event.DomainEvent;
import com.chen.ddd.core.common.logger.Loggers;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

/**
 * 事件总线
 *
 * @author cl
 * @version 1.0
 * @since 2020/10/30
 */
@Named
public class EventBus {

    private static EventPublisher eventPublisher;

    @Inject
    public void setEventPublisher(EventPublisher eventPublisher) {
        if (Objects.isNull(eventPublisher)) {
            throw new IllegalArgumentException("eventPublisher is null.");
        }
        EventBus.eventPublisher = eventPublisher;
    }

    public static void publish(DomainEvent event) {
        eventPublisher.publish(event);

        Loggers.EventBusLogger.log(event);
    }
}
