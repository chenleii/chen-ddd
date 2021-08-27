package com.chen.ddd.core.common.lock;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.locks.Lock;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/24 17:09
 */
@Named
public class Locks {


    private static LockFactory LOCK_FACTORY;

    @Inject
    public Locks(LockFactory lockFactory) {
        LOCK_FACTORY = lockFactory;
    }

    public static Lock create(String lockKey) {
        return LOCK_FACTORY.create(lockKey);
    }

}
