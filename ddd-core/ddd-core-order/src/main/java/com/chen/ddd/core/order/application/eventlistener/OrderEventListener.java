package com.chen.ddd.core.order.application.eventlistener;

import com.chen.ddd.core.order.domain.model.event.ShippedOrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Named;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/8 11:10
 */
@Slf4j
@Named
public class OrderEventListener {

    @Async
    @TransactionalEventListener(ShippedOrderEvent.class)
    public void listener(ShippedOrderEvent event) {
        log.info("接收发货订单事件[{}]." , event);

        // 发送通知
    }

}
