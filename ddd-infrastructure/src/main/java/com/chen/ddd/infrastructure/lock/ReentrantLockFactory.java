package com.chen.ddd.infrastructure.lock;

import com.chen.ddd.core.common.lock.LockFactory;
import org.springframework.integration.support.locks.DefaultLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/30 20:13
 */
// @Named
public class ReentrantLockFactory implements LockFactory {

    private final LockRegistry lockRegistry = new DefaultLockRegistry(1023);

    @Override
    public Lock create(String lockKey) {
        return lockRegistry.obtain(lockKey);
    }

}
