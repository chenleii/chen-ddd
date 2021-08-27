package com.chen.ddd.core.common.logger;

import com.chen.ddd.core.common.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.time.LocalDateTime;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/8 19:36
 */
public class Loggers {

    /**
     * 分隔符
     */
    private static final String DELIMITER = "\u0001";

    private static String getTraceId() {
        return MDC.get("traceId");
    }

    private static LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 事件总线日志记录器
     */
    public static class EventBusLogger {
        private static final Logger logger = LoggerFactory.getLogger(EventBusLogger.class);

        public static void log(DomainEvent domainEvent) {
            logger.info(
                    getTraceId() +
                            DELIMITER +
                            getDateTime() +
                            DELIMITER +
                            domainEvent.code() +
                            DELIMITER +
                            domainEvent.type() +
                            DELIMITER +
                            domainEvent
            );
        }
    }


}
