package com.chen.ddd.core.common.exception;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/22 13:04
 */
public class DomainRuntimeException extends RuntimeException {
    public DomainRuntimeException() {
    }

    public DomainRuntimeException(String message) {
        super(message);
    }

    public DomainRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainRuntimeException(Throwable cause) {
        super(cause);
    }

    public DomainRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
