package com.chen.ddd.interfaces.http.dto.app.outputdto;

import com.chen.ddd.core.order.domain.model.OrderStatus;
import com.chen.ddd.core.order.domain.model.cqrs.result.LogisticsInfoResult;
import com.chen.ddd.core.order.domain.model.cqrs.result.OrderItemResult;
import com.chen.ddd.core.order.domain.model.cqrs.result.ShippingAddressResult;
import com.chen.ddd.interfaces.http.dto.DTO;
import com.chen.ddd.interfaces.http.dto.app.LogisticsInfoDTO;
import com.chen.ddd.interfaces.http.dto.app.ShippingAddressDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.time.Instant;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/30 15:29
 */
@Getter
@Setter
@ToString
@Builder
@ApiModel("订单输出数据")
@Schema(name = "订单输出数据")
public class OrderOutputDTO implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @Schema(name = "id")
    private Long id;

    /**
     * 买家ID
     */
    @ApiModelProperty("买家ID")
    @Schema(name = "买家ID")
    private Long buyerId;

    /**
     * 订单项目列表
     */
    @ApiModelProperty("订单项目列表")
    @Schema(name = "订单项目列表")
    private List<OrderItemOutputDTO> itemList;

    /**
     * 订单总价
     */
    @ApiModelProperty("订单总价")
    @Schema(name = "订单总价")
    private MonetaryAmount totalPrice;

    /**
     * 订单状态
     */
    @ApiModelProperty("订单状态")
    @Schema(name = "订单状态")
    private OrderStatus status;

    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    @Schema(name = "收货地址")
    private ShippingAddressDTO shippingAddress;

    /**
     * 物流信息
     */
    @ApiModelProperty("物流信息")
    @Schema(name = "物流信息")
    private LogisticsInfoDTO logisticsInfo;

    /**
     * 创建于
     */
    @ApiModelProperty("创建于")
    @Schema(name = "创建于")
    private Instant createdAt;
    /**
     * 更新于
     */
    @ApiModelProperty("更新于")
    @Schema(name = "更新于")
    private Instant updatedAt;
}
