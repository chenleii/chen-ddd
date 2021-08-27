package com.chen.ddd.core.common.exception;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/17
 */
public class NotExistException extends DomainRuntimeException {
    public NotExistException() {
    }

    public NotExistException(String message) {
        super(message);
    }

    public NotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistException(Throwable cause) {
        super(cause);
    }

    public NotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
