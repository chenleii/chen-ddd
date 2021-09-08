package com.chen.ddd.core.order.domain.model.cqrs.result;

import com.chen.ddd.core.common.cqrs.Result;
import com.chen.ddd.core.order.port.product.ProductInfo;
import lombok.*;
import org.jddd.core.annotation.ValueObject;

import javax.money.MonetaryAmount;
import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 17:11
 */
@Getter
@Setter
@ToString
public class ProductSnapshotResult implements Result {

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 标题
     */
    private String title;

    /**
     * 明细
     */
    private String details;

    /**
     * 单价
     */
    private MonetaryAmount unitPrice;
}
