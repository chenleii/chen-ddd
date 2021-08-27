package com.chen.ddd.infrastructure.lock;

import com.chen.ddd.core.common.lock.LockFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.locks.Lock;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/30 20:13
 */
@Named
public class RedisLockFactory implements LockFactory {

    @Inject
    private RedisLockRegistry redisLockRegistry;

    @Override
    public Lock create(String lockKey) {
        return redisLockRegistry.obtain(lockKey);
    }

}
