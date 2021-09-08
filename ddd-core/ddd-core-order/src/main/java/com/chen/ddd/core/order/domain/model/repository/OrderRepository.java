package com.chen.ddd.core.order.domain.model.repository;

import com.chen.ddd.core.order.domain.model.Order;
import com.chen.ddd.core.order.domain.model.OrderId;
import com.chen.ddd.core.order.domain.model.OrderItemId;
import com.chen.ddd.core.order.domain.model.exception.OrderNotExistsException;
import org.jddd.core.annotation.Repository;

import java.util.Optional;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 17:34
 */
@Repository
public interface OrderRepository {

    /**
     * 生成ID
     *
     * @return ID
     */
    OrderId generateId();

    /**
     * 生成ID
     *
     * @return ID
     */
    OrderItemId generateOrderItemId();

    /**
     * 根据订单ID获取订单
     *
     * @param orderId 订单ID
     * @return 订单
     */
    Order getById(OrderId orderId);

    /**
     * 根据订单ID获取订单
     * <p>
     * 加锁
     *
     * @param orderId 订单ID
     * @return 订单
     */
    Order getLockById(OrderId orderId);


    /**
     * 根据订单ID获取订单
     * <p>
     * 不存在抛异常
     *
     * @param orderId 订单ID
     * @return 订单
     */
    default Order getByIdNotExistsThrowException(OrderId orderId) {
        Order order = getById(orderId);
        return Optional.ofNullable(order)
                .orElseThrow(() -> new OrderNotExistsException("order not exists. " +
                        "orderId:[" + orderId.getId() + "]."));
    }

    /**
     * 根据订单ID获取订单
     * <p>
     * 加锁
     * 不存在抛异常
     *
     * @param orderId 订单ID
     * @return 订单
     */
    default Order getLockByIdNotExistsThrowException(OrderId orderId) {
        Order order = getLockById(orderId);
        return Optional.ofNullable(order)
                .orElseThrow(() -> new OrderNotExistsException("order not exists. " +
                        "orderId:[" + orderId.getId() + "]."));
    }

    /**
     * 保存订单
     *
     * @param order 订单
     */
    void save(Order order);
}
