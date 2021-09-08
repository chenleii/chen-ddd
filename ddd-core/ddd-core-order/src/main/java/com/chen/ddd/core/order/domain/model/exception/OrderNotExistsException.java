package com.chen.ddd.core.order.domain.model.exception;

import com.chen.ddd.core.common.exception.DomainRuntimeException;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 21:01
 */
public class OrderNotExistsException extends DomainRuntimeException {
    public OrderNotExistsException() {
        super();
    }

    public OrderNotExistsException(String message) {
        super(message);
    }

    public OrderNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotExistsException(Throwable cause) {
        super(cause);
    }

    public OrderNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
