package com.chen.ddd.core.order.domain.model.service;

import com.chen.ddd.core.order.domain.model.Order;
import com.chen.ddd.core.order.port.pay.PayPort;
import lombok.extern.slf4j.Slf4j;
import org.jddd.core.annotation.Service;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 14:50
 */
@Service
@Slf4j
@Named
public class OrderService {

    @Inject
    private PayPort payPort;


    /**
     * 订单支付
     *
     * @param order 订单
     */
    public void pay(Order order) {
        order.pay();
        final boolean pay = payPort.pay(order.getId().getId());
        if (pay) {
            order.paySuccess();
        } else {
            order.payFail();
        }
    }
}
