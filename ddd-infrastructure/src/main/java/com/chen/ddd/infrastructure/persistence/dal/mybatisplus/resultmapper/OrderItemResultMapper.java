package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.resultmapper;

import com.chen.ddd.core.common.objectmapper.SourceToTargetMapper;
import com.chen.ddd.core.order.domain.model.ProductSnapshot;
import com.chen.ddd.core.order.domain.model.cqrs.result.OrderItemResult;
import com.chen.ddd.core.order.domain.model.cqrs.result.ProductSnapshotResult;
import com.chen.ddd.infrastructure.json.JsonSerializer;
import com.chen.ddd.infrastructure.json.JsonSerializers;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.dataobject.OrderItemDO;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper.EnumMapper;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper.IdMapper;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper.MonetaryMapper;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper.TimeMapper;
import org.javamoney.moneta.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 16:56
 */
@Mapper(componentModel = "default" ,
        uses = {IdMapper.class, EnumMapper.class, TimeMapper.class, MonetaryMapper.class,},
        imports = {JsonSerializers.class, Money.class,})
public interface OrderItemResultMapper extends SourceToTargetMapper<OrderItemDO, OrderItemResult> {
    OrderItemResultMapper MAPPER = Mappers.getMapper(OrderItemResultMapper.class);

    @Override
    @Mapping(target = "unitPrice" , expression = "java(Money.of(orderItemDO.getUnitPrice(),orderItemDO.getUnitPriceCurrency()))")
    OrderItemResult sourceToTarget(OrderItemDO orderItemDO);


}
