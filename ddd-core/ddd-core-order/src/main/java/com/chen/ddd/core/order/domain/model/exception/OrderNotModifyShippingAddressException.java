package com.chen.ddd.core.order.domain.model.exception;

import com.chen.ddd.core.common.exception.DomainRuntimeException;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 21:01
 */
public class OrderNotModifyShippingAddressException extends DomainRuntimeException {
    public OrderNotModifyShippingAddressException() {
        super();
    }

    public OrderNotModifyShippingAddressException(String message) {
        super(message);
    }

    public OrderNotModifyShippingAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotModifyShippingAddressException(Throwable cause) {
        super(cause);
    }

    public OrderNotModifyShippingAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
