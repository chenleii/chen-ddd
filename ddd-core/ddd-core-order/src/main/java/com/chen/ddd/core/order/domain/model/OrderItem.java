package com.chen.ddd.core.order.domain.model;

import com.google.common.base.Preconditions;
import lombok.*;
import org.jddd.core.annotation.Entity;

import javax.money.MonetaryAmount;
import java.time.Instant;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 15:27
 */
@Entity
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
public class OrderItem {

    /**
     * id
     */
    private OrderItemId id;

    /**
     * 订单ID
     */
    private OrderId orderId;

    /**
     * 买家ID
     */
    private Long buyerId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品标题
     */
    private String productTitle;

    /**
     * 购买数量
     */
    private Integer quantity;
    /**
     * 单价
     */
    private MonetaryAmount unitPrice;

    /**
     * 商品快照
     */
    private ProductSnapshot productSnapshot;

    /**
     * 创建于
     */
    private Instant createdAt;
    /**
     * 更新于
     */
    private Instant updatedAt;

    public OrderItem(OrderItemId id,
                     OrderId orderId,
                     Long buyerId,
                     Long productId,
                     String productTitle,
                     Integer quantity,
                     MonetaryAmount unitPrice,
                     ProductSnapshot productSnapshot,
                     Instant createdAt,
                     Instant updatedAt
    ) {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(orderId);
        Preconditions.checkNotNull(buyerId);
        Preconditions.checkNotNull(productId);
        Preconditions.checkNotNull(productTitle);
        Preconditions.checkNotNull(quantity);
        Preconditions.checkArgument(quantity > 0);
        Preconditions.checkNotNull(unitPrice);
        Preconditions.checkNotNull(productSnapshot);
        Preconditions.checkNotNull(createdAt);
        Preconditions.checkNotNull(updatedAt);

        this.id = id;
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.productSnapshot = productSnapshot;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static OrderItem create(OrderItemId id,
                                   OrderId orderId,
                                   Long buyerId,
                                   Long productId,
                                   String productTitle,
                                   Integer quantity,
                                   MonetaryAmount unitPrice,
                                   ProductSnapshot productSnapshot
    ) {
        final Instant now = Instant.now();
        final OrderItem orderItem = new OrderItem(
                id,
                orderId,
                buyerId,
                productId,
                productTitle,
                quantity,
                unitPrice,
                productSnapshot,
                now,
                now
        );
        return orderItem;
    }

    /**
     * 修改购买数量
     *
     * @param quantity 数量
     */
    public void modifyQuantity(Integer quantity) {
        Preconditions.checkNotNull(quantity);
        Preconditions.checkArgument(quantity > 0);

        setQuantity(quantity);
        setUpdatedAt(Instant.now());
    }
}
