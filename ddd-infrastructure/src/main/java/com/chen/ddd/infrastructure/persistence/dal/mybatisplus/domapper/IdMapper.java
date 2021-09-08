package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper;

import com.chen.ddd.core.order.domain.model.OrderId;
import com.chen.ddd.core.order.domain.model.OrderItemId;
import org.jddd.core.types.Identifiable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/1 18:35
 */
@Mapper(componentModel = "default")
public interface IdMapper {
    IdMapper MAPPER = Mappers.getMapper(IdMapper.class);

    default Long map(Identifiable<Long> identifiable) {
        if (Objects.isNull(identifiable)) {
            return null;
        }
        return identifiable.getId();
    }

    default OrderId mapOrderId(Long identifiable) {
        if (Objects.isNull(identifiable)) {
            return null;
        }
        return OrderId.of(identifiable);
    }

    default OrderItemId mapOrderItemId(Long identifiable) {
        if (Objects.isNull(identifiable)) {
            return null;
        }
        return OrderItemId.of(identifiable);
    }
}
