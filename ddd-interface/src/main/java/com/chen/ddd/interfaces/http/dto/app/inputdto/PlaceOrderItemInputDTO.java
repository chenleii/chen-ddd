package com.chen.ddd.interfaces.http.dto.app.inputdto;

import com.chen.ddd.interfaces.http.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("订单项输入数据")
public class PlaceOrderItemInputDTO implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    private Long productId;
    /**
     * 扣减数量
     */
    @ApiModelProperty("扣减数量")
    private Integer quantity;
}
