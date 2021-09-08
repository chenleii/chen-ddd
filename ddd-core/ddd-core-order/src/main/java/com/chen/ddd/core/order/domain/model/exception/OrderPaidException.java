package com.chen.ddd.core.order.domain.model.exception;

import com.chen.ddd.core.common.exception.DomainRuntimeException;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 21:01
 */
public class OrderPaidException extends DomainRuntimeException {
    public OrderPaidException() {
        super();
    }

    public OrderPaidException(String message) {
        super(message);
    }

    public OrderPaidException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderPaidException(Throwable cause) {
        super(cause);
    }

    public OrderPaidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
