package com.chen.ddd.infrastructure.eventbus;

import com.chen.ddd.core.common.event.DomainEvent;
import com.chen.ddd.core.common.eventbus.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/27 16:25
 */
@Named
public class SpringEventPublisher implements EventPublisher {

    @Inject
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
