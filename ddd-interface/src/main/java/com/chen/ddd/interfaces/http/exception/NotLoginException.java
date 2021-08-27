package com.chen.ddd.interfaces.http.exception;

/**
 * @author cl
 * @version 1.0
 * @since 2020/12/2 19:23
 */
public class NotLoginException extends RuntimeException {
    public NotLoginException() {
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }

    public NotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
