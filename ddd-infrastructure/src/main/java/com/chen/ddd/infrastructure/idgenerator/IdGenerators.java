package com.chen.ddd.infrastructure.idgenerator;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/2
 */
public class IdGenerators {
    private static final SnowFlakeIdGenerator INSTANCE = new SnowFlakeIdGenerator(1L, 1L);

    public static Long generateId() {
        return INSTANCE.generateId();
    }
}
