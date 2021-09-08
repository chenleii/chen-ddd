package com.chen.ddd.core.order.port.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.money.MonetaryAmount;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 15:32
 */
@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
public class ProductInfo {

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
