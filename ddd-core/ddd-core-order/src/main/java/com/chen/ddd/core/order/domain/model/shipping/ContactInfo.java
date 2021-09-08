package com.chen.ddd.core.order.domain.model.shipping;

import lombok.*;
import org.jddd.core.annotation.ValueObject;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 16:42
 */
@ValueObject
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor(staticName = "of")
@Builder
public class ContactInfo {

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private PhoneNumber phone;
}
