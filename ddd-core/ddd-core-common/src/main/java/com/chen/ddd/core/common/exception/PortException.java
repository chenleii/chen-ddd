package com.chen.ddd.core.common.exception;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/23 11:33
 */
public class PortException extends DomainRuntimeException {
    public PortException() {
        super();
    }

    public PortException(String message) {
        super(message);
    }

    public PortException(String message, Throwable cause) {
        super(message, cause);
    }

    public PortException(Throwable cause) {
        super(cause);
    }

    public PortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
