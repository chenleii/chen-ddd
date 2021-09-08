package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper;

import com.chen.ddd.core.common.enums.DomainEnum;
import com.chen.ddd.core.order.domain.model.OrderStatus;
import com.chen.ddd.core.order.domain.model.logistics.LogisticsStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/1 18:35
 */
@Mapper(componentModel = "default")
public interface EnumMapper {
    EnumMapper MAPPER = Mappers.getMapper(EnumMapper.class);

    default Integer map(DomainEnum e) {
        if (Objects.isNull(e)) {
            return null;
        }
        return e.getValue();
    }


    default OrderStatus mapOrderStatus(Integer value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return OrderStatus.of(value);
    }

    default LogisticsStatus mapLogisticsStatus(Integer value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return LogisticsStatus.of(value);
    }

}
