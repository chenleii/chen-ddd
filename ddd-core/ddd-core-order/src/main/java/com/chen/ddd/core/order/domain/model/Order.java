package com.chen.ddd.core.order.domain.model;

import com.chen.ddd.core.common.event.DomainEventPublisher;
import com.chen.ddd.core.order.domain.model.event.FinishedOrderEvent;
import com.chen.ddd.core.order.domain.model.event.PaySuccessfulOrderEvent;
import com.chen.ddd.core.order.domain.model.event.PrepaidOrderEvent;
import com.chen.ddd.core.order.domain.model.event.ShippedOrderEvent;
import com.chen.ddd.core.order.domain.model.exception.OrderNotModifyShippingAddressException;
import com.chen.ddd.core.order.domain.model.exception.OrderPaidException;
import com.chen.ddd.core.order.domain.model.logistics.LogisticsInfo;
import com.chen.ddd.core.order.domain.model.logistics.LogisticsStatus;
import com.chen.ddd.core.order.domain.model.shipping.ShippingAddress;
import com.google.common.base.Preconditions;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.jddd.core.annotation.AggregateRoot;

import javax.money.MonetaryAmount;
import java.time.Instant;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 15:27
 */
@AggregateRoot
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
public class Order implements DomainEventPublisher {

    /**
     * id
     */
    private OrderId id;

    /**
     * 买家ID
     */
    private Long buyerId;

    /**
     * 订单项目列表
     */
    private List<OrderItem> itemList;

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
    private ShippingAddress shippingAddress;

    /**
     * 物流信息
     */
    private LogisticsInfo logisticsInfo;

    /**
     * 创建于
     */
    private Instant createdAt;
    /**
     * 更新于
     */
    private Instant updatedAt;

    public Order(OrderId id,
                 Long buyerId,
                 List<OrderItem> itemList,
                 MonetaryAmount totalPrice,
                 OrderStatus status,
                 ShippingAddress shippingAddress,
                 LogisticsInfo logisticsInfo,
                 Instant createdAt,
                 Instant updatedAt) {

        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(buyerId);
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(itemList));
        Preconditions.checkNotNull(totalPrice);
        Preconditions.checkNotNull(status);
        Preconditions.checkNotNull(shippingAddress);
        Preconditions.checkNotNull(logisticsInfo);
        Preconditions.checkNotNull(createdAt);
        Preconditions.checkNotNull(updatedAt);

        this.id = id;
        this.buyerId = buyerId;
        this.itemList = itemList;
        this.totalPrice = totalPrice;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.logisticsInfo = logisticsInfo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * 创建订单
     *
     * @param id              订单ID
     * @param buyerId         买家ID
     * @param itemList        订单项列表
     * @param shippingAddress 收货地址
     * @return 订单
     */
    public static Order create(OrderId id,
                               Long buyerId,
                               List<OrderItem> itemList,
                               ShippingAddress shippingAddress) {

        Preconditions.checkArgument(CollectionUtils.isNotEmpty(itemList));

        final MonetaryAmount totalPrice = itemList.stream()
                .map(OrderItem::getUnitPrice)
                .reduce(MonetaryAmount::add)
                .orElseThrow(RuntimeException::new);
        final Instant now = Instant.now();
        final Order order = new Order(
                id,
                buyerId,
                itemList,
                totalPrice,
                OrderStatus.WAIT_PAY,
                shippingAddress,
                LogisticsInfo.empty(),
                now,
                now
        );

        order.publishEvent(
                PrepaidOrderEvent.create(order)
        );

        return order;
    }

    /**
     * 订单修改收货地址
     *
     * @param shippingAddress 收货地址
     */
    public void updateShippingAddress(ShippingAddress shippingAddress) {
        Preconditions.checkNotNull(shippingAddress);

        if (getStatus() != OrderStatus.WAIT_PAY) {
            throw new OrderNotModifyShippingAddressException("order not modify shipping address.");
        }

        setShippingAddress(shippingAddress);
        setUpdatedAt(Instant.now());
    }

    /**
     * 订单支付
     * <p>
     * 判断是否可以支付
     * 变更订单状态为支付中
     */
    public void pay() {
        if (getStatus() != OrderStatus.WAIT_PAY) {
            throw new OrderPaidException("order paid.");
        }

        setStatus(OrderStatus.PAYING);
        setUpdatedAt(Instant.now());
    }

    /**
     * 订单支付失败
     */
    public void payFail() {
        setStatus(OrderStatus.WAIT_PAY);
        setUpdatedAt(Instant.now());
    }

    /**
     * 订单支付成功
     */
    public void paySuccess() {
        setStatus(OrderStatus.WAIT_SHIPPING);
        setUpdatedAt(Instant.now());

        publishEvent(
                PaySuccessfulOrderEvent.create(this)
        );
    }

    /**
     * 订单发货
     *
     * @param mailNo 运单号
     */
    public void ship(String mailNo) {
        Preconditions.checkNotNull(mailNo);

        setStatus(OrderStatus.SHIPPED);
        final LogisticsInfo updateLogisticsInfo = getLogisticsInfo().update(mailNo, LogisticsStatus.IN_TRANSIT);
        setLogisticsInfo(updateLogisticsInfo);
        setUpdatedAt(Instant.now());

        publishEvent(
                ShippedOrderEvent.create(this)
        );
    }

    /**
     * 订单完结
     */
    public void finish() {
        setStatus(OrderStatus.FINISHED);
        setUpdatedAt(Instant.now());

        publishEvent(
                FinishedOrderEvent.create(this)
        );
    }
}
