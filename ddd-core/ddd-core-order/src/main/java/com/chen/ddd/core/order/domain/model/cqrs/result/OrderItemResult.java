package com.chen.ddd.core.order.domain.model.cqrs.result;

import com.chen.ddd.core.common.cqrs.Result;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.time.Instant;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 20:32
 */
@Getter
@Setter
@ToString
public class OrderItemResult implements Result {

    /**
     * id
     */
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

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
    private ProductSnapshotResult productSnapshot;

    /**
     * 创建于
     */
    private Instant createdAt;
    /**
     * 更新于
     */
    private Instant updatedAt;
}
