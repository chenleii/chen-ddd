package com.chen.ddd.interfaces.http.dto.app.outputdto;

import com.chen.ddd.interfaces.http.dto.DTO;
import com.chen.ddd.interfaces.http.dto.app.ProductSnapshotDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.time.Instant;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 20:32
 */
@Getter
@Setter
@ToString
@ApiModel("订单项输出数据")
@Schema(name = "订单项输出数据")
public class OrderItemOutputDTO implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @Schema(name = "id")
    private Long id;

    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
    @Schema(name = "订单ID")
    private Long orderId;

    /**
     * 买家ID
     */
    @ApiModelProperty("买家ID")
    @Schema(name = "买家ID")
    private Long buyerId;

    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    @Schema(name = "商品ID")
    private Long productId;

    /**
     * 商品标题
     */
    @ApiModelProperty("商品标题")
    @Schema(name = "商品标题")
    private String productTitle;

    /**
     * 购买数量
     */
    @ApiModelProperty("购买数量")
    @Schema(name = "购买数量")
    private Integer quantity;
    /**
     * 单价
     */
    @ApiModelProperty("单价")
    @Schema(name = "单价")
    private MonetaryAmount unitPrice;

    /**
     * 商品快照
     */
    @ApiModelProperty("商品快照")
    @Schema(name = "商品快照")
    private ProductSnapshotDTO productSnapshot;

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
