package com.chen.ddd.core.order.domain.model.shipping;

import lombok.*;
import org.jddd.core.annotation.ValueObject;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 17:03
 */
@ValueObject
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor(staticName = "of")
@Builder
public class ShippingAddress {

    /**
     * 地址
     */
    private Address address;

    /**
     * 联系人信息
     */
    private ContactInfo contactInfo;

}
