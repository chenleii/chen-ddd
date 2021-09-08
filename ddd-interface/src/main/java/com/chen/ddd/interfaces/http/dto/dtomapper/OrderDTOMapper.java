package com.chen.ddd.interfaces.http.dto.dtomapper;

import com.chen.ddd.core.common.objectmapper.SourceToTargetMapper;
import com.chen.ddd.core.order.domain.model.cqrs.result.OrderResult;
import com.chen.ddd.interfaces.http.dto.app.outputdto.OrderOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface OrderDTOMapper extends SourceToTargetMapper<OrderResult, OrderOutputDTO> {
    OrderDTOMapper MAPPER = Mappers.getMapper(OrderDTOMapper.class);


    @Override
    OrderOutputDTO sourceToTarget(OrderResult orderResult);
}