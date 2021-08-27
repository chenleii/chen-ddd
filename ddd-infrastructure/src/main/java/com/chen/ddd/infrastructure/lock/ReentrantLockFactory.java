package com.chen.ddd.infrastructure.lock;

import com.chen.ddd.core.common.lock.LockFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/30 20:13
 */
// @Named
public class ReentrantLockFactory implements LockFactory {


    @Override
    public Lock create(String lockKey) {
        return new ReentrantLock();
    }

}
