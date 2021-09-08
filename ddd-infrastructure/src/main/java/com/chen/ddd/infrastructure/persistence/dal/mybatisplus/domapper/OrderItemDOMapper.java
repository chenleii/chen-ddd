package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper;

import com.chen.ddd.core.common.objectmapper.ObjectMapper;
import com.chen.ddd.core.order.domain.model.OrderItem;
import com.chen.ddd.core.order.domain.model.ProductSnapshot;
import com.chen.ddd.infrastructure.json.JsonSerializers;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.dataobject.OrderItemDO;
import org.javamoney.moneta.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 16:46
 */
@Mapper(componentModel = "default" ,
        uses = {IdMapper.class, EnumMapper.class, TimeMapper.class, MonetaryMapper.class,},
        imports = {JsonSerializers.class, Money.class,})
public interface OrderItemDOMapper extends ObjectMapper<OrderItem, OrderItemDO> {
    OrderItemDOMapper MAPPER = Mappers.getMapper(OrderItemDOMapper.class);

    @Override
    @Mapping(target = "unitPriceCurrency" , source = "unitPrice")
    OrderItemDO sourceToTarget(OrderItem orderItem);

    @Override
    @Mapping(target = "unitPrice" , expression = "java(Money.of(orderItemDO.getUnitPrice(),orderItemDO.getUnitPriceCurrency()))")
    OrderItem targetToSource(OrderItemDO orderItemDO);

}
