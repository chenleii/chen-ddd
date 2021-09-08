package com.chen.ddd.infrastructure.port.pay;

import com.chen.ddd.core.order.port.pay.PayPort;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 18:37
 */
@Slf4j
@Named
public class PayPortImpl implements PayPort {
    @Override
    public boolean pay(Long orderId) {
        //省略调用外部服务

        return true;
    }
}
