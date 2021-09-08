package com.chen.ddd.core.order.domain.model.exception;

import com.chen.ddd.core.common.exception.DomainRuntimeException;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 10:17
 */
public class OrderReduceInventoryException  extends DomainRuntimeException {
    public OrderReduceInventoryException() {
        super();
    }

    public OrderReduceInventoryException(String message) {
        super(message);
    }

    public OrderReduceInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderReduceInventoryException(Throwable cause) {
        super(cause);
    }

    public OrderReduceInventoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
