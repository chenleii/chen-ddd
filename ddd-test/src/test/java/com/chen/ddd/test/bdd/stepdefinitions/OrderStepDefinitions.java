package com.chen.ddd.test.bdd.stepdefinitions;

import com.chen.ddd.core.order.application.commandservice.OrderCommandService;
import com.chen.ddd.core.order.application.queryservice.OrderQueryService;
import com.chen.ddd.core.order.domain.model.OrderStatus;
import com.chen.ddd.core.order.domain.model.cqrs.command.CreatePrepaidOrderCommand;
import com.chen.ddd.core.order.domain.model.cqrs.command.FinishOrderCommand;
import com.chen.ddd.core.order.domain.model.cqrs.command.PayOrderCommand;
import com.chen.ddd.core.order.domain.model.cqrs.command.ShipOrderCommand;
import com.chen.ddd.core.order.domain.model.cqrs.query.OrderQuery;
import com.chen.ddd.core.order.domain.model.cqrs.result.OrderResult;
import com.chen.ddd.core.order.domain.model.shipping.Address;
import com.chen.ddd.core.order.domain.model.shipping.ContactInfo;
import com.chen.ddd.core.order.domain.model.shipping.PhoneNumber;
import com.chen.ddd.core.order.domain.model.shipping.ShippingAddress;
import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;

import javax.inject.Inject;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/15 10:23
 */
public class OrderStepDefinitions {

    @Inject
    private OrderCommandService orderCommandService;
    @Inject
    private OrderQueryService orderQueryService;

    private Long orderId;

    @假如("已有商品")
    public void 已有商品() {
        // 不用处理
    }


    @当("买家下单")
    public void 买家下单() {
        final ShippingAddress shippingAddress = ShippingAddress.builder()
                .address(
                        Address.builder()
                                .country("")
                                .province("")
                                .city("")
                                .district("")
                                .street("")
                                .details("")
                                .build()
                )
                .contactInfo(
                        ContactInfo.builder()
                                .name("test")
                                .phone(PhoneNumber.of("+86" , "111"))
                                .build()
                )
                .build();
        final CreatePrepaidOrderCommand createPrepaidOrderCommand = CreatePrepaidOrderCommand.builder()
                .buyerId(1L)
                .shippingAddress(shippingAddress)
                .itemList(Arrays.asList(
                        CreatePrepaidOrderCommand.Item.of(1L, 1),
                        CreatePrepaidOrderCommand.Item.of(2L, 2)
                ))
                .build();
        this.orderId = orderCommandService.createPrepaidOrder(
                createPrepaidOrderCommand
        );
    }


    @那么("订单状态为待支付")
    public void 订单状态为待支付() {
        final OrderResult orderResult = orderQueryService.query(OrderQuery.builder().orderId(this.orderId).build());
        assertThat(orderResult)
                .isNotNull()
                .extracting(OrderResult::getStatus)
                .isNotNull()
                .isEqualTo(OrderStatus.WAIT_PAY);
    }

    @当("买家支付订单")
    public void 买家支付订单() {
        orderCommandService.payOrder(
                PayOrderCommand.builder()
                        .orderId(this.orderId)
                        .build()
        );
    }

    @那么("订单状态为待发货")
    public void 订单状态为待发货() {
        final OrderResult orderResult = orderQueryService.query(OrderQuery.builder().orderId(this.orderId).build());
        assertThat(orderResult)
                .isNotNull()
                .extracting(OrderResult::getStatus)
                .isNotNull()
                .isEqualTo(OrderStatus.WAIT_SHIPPING);
    }


    @当("订单发货")
    public void 订单发货() {
        orderCommandService.shipOrder(
                ShipOrderCommand.builder()
                        .orderId(this.orderId)
                        .mailNo("111")
                        .build()
        );
    }


    @那么("订单状态为已发货")
    public void 订单状态为已发货() {
        final OrderResult orderResult = orderQueryService.query(OrderQuery.builder().orderId(this.orderId).build());
        assertThat(orderResult)
                .isNotNull()
                .extracting(OrderResult::getStatus)
                .isNotNull()
                .isEqualTo(OrderStatus.SHIPPED);
    }


    @当("买家确认收货")
    public void 买家确认收货() {
       orderCommandService.finishOrder(
               FinishOrderCommand.builder()
                       .orderId(this.orderId)
                       .build()
       );
    }


    @那么("订单状态为已完结")
    public void 订单状态为已完结() {
        final OrderResult orderResult = orderQueryService.query(OrderQuery.builder().orderId(this.orderId).build());
        assertThat(orderResult)
                .isNotNull()
                .extracting(OrderResult::getStatus)
                .isNotNull()
                .isEqualTo(OrderStatus.FINISHED);
    }
}
