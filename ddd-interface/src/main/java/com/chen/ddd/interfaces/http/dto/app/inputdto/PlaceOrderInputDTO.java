package com.chen.ddd.interfaces.http.dto.app.inputdto;

import com.chen.ddd.interfaces.http.dto.DTO;
import com.chen.ddd.interfaces.http.dto.app.ShippingAddressDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/30 16:18
 */
@Getter
@Setter
@ToString
@ApiModel("买家下单输入数据")
@Schema(name = "买家下单输入数据")
public class PlaceOrderInputDTO implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * 订单项目列表
     */
    @ApiModelProperty("订单项目列表")
    @Schema(name = "订单项目列表")
    private List<PlaceOrderItemInputDTO> itemList;

    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    @Schema(name = "收货地址")
    private ShippingAddressDTO shippingAddress;


}
