package com.chen.ddd.core.order.domain.model;

import com.chen.ddd.core.common.enums.DomainEnum;

import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 16:19
 */
public enum OrderStatus implements DomainEnum {

    /**
     * 待付款
     */
    WAIT_PAY(10),

    /**
     * 支付中
     */
    PAYING(20),

    /**
     * 待发货
     */
    WAIT_SHIPPING(50),

    /**
     * 已发货
     */
    SHIPPED(60),

    /**
     * 已完成
     */
    FINISHED(90);


    ;


    private final Integer status;

    OrderStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Integer getValue() {
        return this.status;
    }

    public static OrderStatus of(Integer status) {
        final OrderStatus[] values = values();
        for (OrderStatus value : values) {
            if (Objects.equals(value.getValue(),status)) {
                return value;
            }
        }

        return null;
    }
}
