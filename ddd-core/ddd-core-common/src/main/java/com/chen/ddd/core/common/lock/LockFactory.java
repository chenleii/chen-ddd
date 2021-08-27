package com.chen.ddd.core.common.lock;

import java.util.concurrent.locks.Lock;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/24 17:13
 */
public interface LockFactory {

    /**
     * 创建锁
     *
     * @param lockKey 锁标识
     * @return 锁
     */
    Lock create(String lockKey);
}
