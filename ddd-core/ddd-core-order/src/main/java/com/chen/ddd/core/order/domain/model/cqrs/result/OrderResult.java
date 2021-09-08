package com.chen.ddd.core.order.domain.model.cqrs.result;

import com.chen.ddd.core.common.cqrs.Result;
import com.chen.ddd.core.order.domain.model.OrderId;
import com.chen.ddd.core.order.domain.model.OrderItem;
import com.chen.ddd.core.order.domain.model.OrderStatus;
import com.chen.ddd.core.order.domain.model.logistics.LogisticsInfo;
import com.chen.ddd.core.order.domain.model.shipping.ShippingAddress;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.time.Instant;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 20:32
 */
@Getter
@Setter
@ToString
public class OrderResult implements Result {


    /**
     * id
     */
    private Long id;

    /**
     * 买家ID
     */
    private Long buyerId;

    /**
     * 订单项目列表
     */
    private List<OrderItemResult> itemList;

    /**
     * 订单总价
     */
    private MonetaryAmount totalPrice;

    /**
     * 订单状态
     */
    private OrderStatus status;

    /**
     * 收货地址
     */
    private ShippingAddressResult shippingAddress;

    /**
     * 物流信息
     */
    private LogisticsInfoResult logisticsInfo;

    /**
     * 创建于
     */
    private Instant createdAt;
    /**
     * 更新于
     */
    private Instant updatedAt;
}
