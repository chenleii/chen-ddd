package com.chen.ddd.core.order.domain.model.logistics;

import com.chen.ddd.core.common.enums.DomainEnum;

import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 17:32
 */
public enum LogisticsStatus implements DomainEnum {

    /**
     * 无配送信息
     */
    NONE(10),

    /**
     * 运输中
     */
    IN_TRANSIT(20),

    /**
     * 待配送
     */
    WAIT_DELIVERY(50),

    /**
     * 配送中
     */
    DELIVERING(60),

    /**
     * 已送达
     */
    DELIVERED(70),

    /**
     * 待取货
     */
    WAIT_FETCH(80),

    /**
     * 已取货
     */
    FETCHED(90);


    private final Integer status;

    LogisticsStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Integer getValue() {
        return this.status;
    }

    public static LogisticsStatus of(Integer status) {
        final LogisticsStatus[] values = values();
        for (LogisticsStatus value : values) {
            if (Objects.equals(value.getValue(), status)) {
                return value;
            }
        }

        return null;
    }
}
