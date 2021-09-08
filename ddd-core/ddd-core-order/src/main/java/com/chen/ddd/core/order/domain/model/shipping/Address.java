package com.chen.ddd.core.order.domain.model.shipping;

import lombok.*;
import org.jddd.core.annotation.ValueObject;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 16:31
 */
@ValueObject
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor(staticName = "of")
@Builder
public class Address {

    /**
     * 国家
     */
    private String country;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 街道
     */
    private String street;
    /**
     * 详细地址
     */
    private String details;


}
