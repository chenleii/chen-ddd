package com.chen.ddd.core.order.domain.model.cqrs.query;

import com.chen.ddd.core.common.cqrs.Query;
import lombok.Builder;
import lombok.Getter;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 20:43
 */
@Builder
@Getter
public class OrderQuery implements Query {

    /**
     * 订单ID
     */
    private final Long orderId;
}
