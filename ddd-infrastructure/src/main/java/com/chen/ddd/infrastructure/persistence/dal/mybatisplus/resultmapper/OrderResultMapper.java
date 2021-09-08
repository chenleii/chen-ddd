package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.resultmapper;

import com.chen.ddd.core.common.objectmapper.SourceToTargetMapper;
import com.chen.ddd.core.order.domain.model.cqrs.result.OrderResult;
import com.chen.ddd.infrastructure.json.JsonSerializers;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.dataobject.OrderDO;
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
        uses = {IdMapper.class, EnumMapper.class, TimeMapper.class, MonetaryMapper.class, OrderItemResultMapper.class,},
        imports = {JsonSerializers.class, Money.class,})
public interface OrderResultMapper extends SourceToTargetMapper<OrderDO, OrderResult> {
    OrderResultMapper MAPPER = Mappers.getMapper(OrderResultMapper.class);

    @Override
    @Mapping(target = "logisticsInfo.trackingNo" , source = "logisticsTrackingNo")
    @Mapping(target = "logisticsInfo.status" , source = "logisticsStatus")
    @Mapping(target = "totalPrice" , expression = "java(Money.of(orderDO.getTotalPrice(),orderDO.getTotalPriceCurrency()))")
    OrderResult sourceToTarget(OrderDO orderDO);

}
