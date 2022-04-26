package com.chen.ddd.interfaces.http.dto.app.inputdto;

import com.chen.ddd.interfaces.http.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@ApiModel("买家支付订单输入数据")
@Schema(name = "买家支付订单输入数据")
public class PayOrderInputDTO implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
    @Schema(name = "订单ID")
    private Long orderId;


}
