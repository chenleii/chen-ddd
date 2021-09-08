package com.chen.ddd.core.order.port.pay;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 14:41
 */
public interface PayPort {

    /**
     * 支付
     *
     * @param orderId 订单ID
     * @return 支付结果：成功/失败
     */
    boolean pay(Long orderId);
}
