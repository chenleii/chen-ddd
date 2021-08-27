package com.chen.ddd.infrastructure.idgenerator;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/2
 */
public class SnowFlakeIdGenerator implements IdGenerator {

    private SnowFlake snowFlake;

    public SnowFlakeIdGenerator(long dataCenterId, long machineId) {
        this.snowFlake = new SnowFlake(dataCenterId, machineId);
    }

    @Override
    public long generateId() {
        return this.snowFlake.nextId();
    }
}
