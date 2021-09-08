package com.chen.ddd.core.order.domain.model;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.jddd.core.annotation.ValueObject;
import org.jddd.core.types.Identifiable;
import org.jddd.core.types.Identifier;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 16:10
 */
@ValueObject
@EqualsAndHashCode(of = {"id"})
@ToString
@Setter(AccessLevel.PRIVATE)
public class OrderItemId implements Identifiable<Long>, Identifier {
    /**
     * id
     */
    private Long id;

    private OrderItemId(Long id) {
        setId(id);
    }

    public static OrderItemId of(Long id) {
        Preconditions.checkNotNull(id);
        return new OrderItemId(id);
    }

    @Override
    public Long getId() {
        return id;
    }
}
