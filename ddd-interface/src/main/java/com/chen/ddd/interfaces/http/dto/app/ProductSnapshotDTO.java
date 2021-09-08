package com.chen.ddd.interfaces.http.dto.app;

import com.chen.ddd.interfaces.http.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.money.MonetaryAmount;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 17:11
 */
@Getter
@Setter
@ToString
@ApiModel("订单商品快照")
public class ProductSnapshotDTO implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    private Long productId;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 明细
     */
    @ApiModelProperty("明细")
    private String details;

    /**
     * 单价
     */
    @ApiModelProperty("单价")
    private MonetaryAmount unitPrice;
}
