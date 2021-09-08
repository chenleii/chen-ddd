package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper;

import com.chen.ddd.core.common.objectmapper.ObjectMapper;
import com.chen.ddd.core.order.domain.model.Order;
import com.chen.ddd.core.order.domain.model.shipping.ShippingAddress;
import com.chen.ddd.infrastructure.json.JsonSerializers;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.dataobject.OrderDO;
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
        uses = {IdMapper.class, EnumMapper.class, TimeMapper.class, MonetaryMapper.class, OrderItemDOMapper.class,},
        imports = {JsonSerializers.class, Money.class,})
public interface OrderDOMapper extends ObjectMapper<Order, OrderDO> {
    OrderDOMapper MAPPER = Mappers.getMapper(OrderDOMapper.class);

    @Override
    @Mapping(target = "totalPriceCurrency" , source = "totalPrice")
    @Mapping(target = "logisticsTrackingNo" , source = "logisticsInfo.trackingNo")
    @Mapping(target = "logisticsStatus" , source = "logisticsInfo.status")
    OrderDO sourceToTarget(Order order);

    @Override
    @Mapping(target = "logisticsInfo.trackingNo" , source = "logisticsTrackingNo")
    @Mapping(target = "logisticsInfo.status" , source = "logisticsStatus")
    @Mapping(target = "totalPrice" , expression = "java(Money.of(orderDO.getTotalPrice(),orderDO.getTotalPriceCurrency()))")
    Order targetToSource(OrderDO orderDO);


}
