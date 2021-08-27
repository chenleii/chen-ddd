package com.chen.ddd.infrastructure.idgenerator;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/2
 */
public interface IdGenerator {

    /**
     * 生成id
     *
     * @return id
     */
    long generateId();
}
