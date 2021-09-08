package com.chen.ddd.infrastructure.port.product;

import com.chen.ddd.core.order.port.product.ProductInfo;
import com.chen.ddd.core.order.port.product.ProductPort;
import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;

import javax.inject.Named;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 18:39
 */
@Named
@Slf4j
public class ProductPortImpl implements ProductPort {
    @Override
    public ProductInfo getProductInfo(Long productId) {
        //省略调用外部服务
        
        return ProductInfo.of(productId, "商品标题" , "商品明细" , Money.of(1, "CNY"));
    }
}
