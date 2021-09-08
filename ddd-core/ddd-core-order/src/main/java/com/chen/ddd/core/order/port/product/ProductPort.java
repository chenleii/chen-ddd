package com.chen.ddd.core.order.port.product;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 15:31
 */
public interface ProductPort {

    /**
     * 获取商品信息
     *
     * @param productId 商品ID
     * @return 商品信息
     */
    ProductInfo getProductInfo(Long productId);
}
