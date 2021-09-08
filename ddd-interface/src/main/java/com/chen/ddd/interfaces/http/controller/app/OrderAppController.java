package com.chen.ddd.interfaces.http.controller.app;

import com.chen.ddd.core.order.application.commandservice.OrderCommandService;
import com.chen.ddd.core.order.application.queryservice.OrderQueryService;
import com.chen.ddd.core.order.domain.model.cqrs.command.CreatePrepaidOrderCommand;
import com.chen.ddd.core.order.domain.model.cqrs.command.PayOrderCommand;
import com.chen.ddd.core.order.domain.model.cqrs.query.OrderQuery;
import com.chen.ddd.core.order.domain.model.cqrs.result.OrderResult;
import com.chen.ddd.core.order.domain.model.shipping.Address;
import com.chen.ddd.core.order.domain.model.shipping.ContactInfo;
import com.chen.ddd.core.order.domain.model.shipping.PhoneNumber;
import com.chen.ddd.core.order.domain.model.shipping.ShippingAddress;
import com.chen.ddd.interfaces.http.dto.app.inputdto.PayOrderInputDTO;
import com.chen.ddd.interfaces.http.dto.app.inputdto.PlaceOrderInputDTO;
import com.chen.ddd.interfaces.http.dto.app.outputdto.OrderOutputDTO;
import com.chen.ddd.interfaces.http.dto.dtomapper.OrderDTOMapper;
import com.chen.ddd.interfaces.http.handle.ResultWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.stream.Collectors;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/8 15:24
 */
@Api(tags = "订单相关的应用接口")
@Slf4j
@RestController
@RequestMapping("/app/order")
public class OrderAppController extends AbstractAppController {


    @Inject
    private OrderCommandService orderCommandService;
    @Inject
    private OrderQueryService orderQueryService;

    @ApiOperation("买家下单")
    @PostMapping("/placeOrder")
    @ResultWrapper
    public Long placeOrder(@RequestBody PlaceOrderInputDTO dto) {
        final ShippingAddress shippingAddress = ShippingAddress.builder()
                .address(
                        Address.builder()
                                .country(dto.getShippingAddress().getAddress().getCountry())
                                .province(dto.getShippingAddress().getAddress().getProvince())
                                .city(dto.getShippingAddress().getAddress().getCity())
                                .district(dto.getShippingAddress().getAddress().getDistrict())
                                .street(dto.getShippingAddress().getAddress().getStreet())
                                .details(dto.getShippingAddress().getAddress().getDetails())
                                .build()
                )
                .contactInfo(
                        ContactInfo.builder()
                                .name(dto.getShippingAddress().getContactInfo().getName())
                                .phone(PhoneNumber.of(dto.getShippingAddress().getContactInfo().getPhone().getAreaCode(), dto.getShippingAddress().getContactInfo().getPhone().getNumber()))
                                .build()
                )
                .build();
        final CreatePrepaidOrderCommand createPrepaidOrderCommand = CreatePrepaidOrderCommand.builder()
                .buyerId(getCurrentBuyerId())
                .shippingAddress(shippingAddress)
                .itemList(
                        dto.getItemList().stream()
                                .map((item) -> CreatePrepaidOrderCommand.Item.of(item.getProductId(), item.getQuantity()))
                                .collect(Collectors.toList())
                )
                .build();
        return orderCommandService.createPrepaidOrder(
                createPrepaidOrderCommand
        );
    }

    @ApiOperation("买家支付订单")
    @PostMapping("/pay")
    @ResultWrapper
    public void pay(@RequestBody PayOrderInputDTO dto) {
        orderCommandService.payOrder(
                PayOrderCommand.builder().orderId(dto.getOrderId()).build()
        );
    }

    @ApiOperation("订单查询")
    @GetMapping("/query")
    @ResultWrapper
    public OrderOutputDTO query(Long orderId) {
        final OrderResult orderResult = orderQueryService.query(OrderQuery.builder().orderId(orderId).build());
        return OrderDTOMapper.MAPPER.sourceToTarget(orderResult);
    }

}
