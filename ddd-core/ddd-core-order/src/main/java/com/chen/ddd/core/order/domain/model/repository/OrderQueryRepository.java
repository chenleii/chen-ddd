package com.chen.ddd.core.order.domain.model.repository;

import com.chen.ddd.core.order.domain.model.cqrs.result.OrderResult;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 17:34
 */
public interface OrderQueryRepository {

    /**
     * 根据订单ID获取订单
     *
     * @param orderId 订单ID
     * @return 订单
     */
    OrderResult queryById(Long orderId);

}
