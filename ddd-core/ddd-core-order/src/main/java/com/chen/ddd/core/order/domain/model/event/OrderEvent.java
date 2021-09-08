package com.chen.ddd.core.order.domain.model.event;

import com.chen.ddd.core.common.event.DomainEvent;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 21:22
 */
@SuperBuilder
@Getter
@ToString
public abstract class OrderEvent implements DomainEvent {
}
