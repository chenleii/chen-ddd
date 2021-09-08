package com.chen.ddd.core.order.domain.model.event;

import com.chen.ddd.core.order.domain.model.Order;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 21:23
 */
@SuperBuilder
@Getter
@ToString(callSuper = true)
public class PaySuccessfulOrderEvent extends OrderEvent{

    public static PaySuccessfulOrderEvent create(Order order) {
        return PaySuccessfulOrderEvent.builder().build();
    }
}
