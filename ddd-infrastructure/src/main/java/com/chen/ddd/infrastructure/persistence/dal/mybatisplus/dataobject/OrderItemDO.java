package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单项表
 * </p>
 *
 * @author chen
 * @since 2021-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "`order_item`" , autoResultMap = true)
public class OrderItemDO implements Serializable {


    /**
     * 订单项ID
     */
    @TableId(value = "id" , type = IdType.INPUT)
    private Long id;

    /**
     * 订单ID
     */
    @TableField("`order_id`")
    private Long orderId;

    /**
     * 买家ID
     */
    @TableField("`buyer_id`")
    private Long buyerId;

    /**
     * 商品ID
     */
    @TableField("`product_id`")
    private Long productId;

    /**
     * 商品标题
     */
    @TableField("`product_title`")
    private String productTitle;

    /**
     * 购买数量
     */
    @TableField("`quantity`")
    private Integer quantity;

    /**
     * 订单单价货币
     */
    @TableField("`unit_price_currency`")
    private String unitPriceCurrency;

    /**
     * 订单单价
     */
    @TableField("`unit_price`")
    private BigDecimal unitPrice;

    /**
     * 商品快照
     */
    @TableField(value = "`product_snapshot`", typeHandler = JacksonTypeHandler.class)
    private ProductSnapshotDO productSnapshot;

    /**
     * 账户创建于
     */
    @TableField("`created_at`")
    private Long createdAt;

    /**
     * 账户更新于
     */
    @TableField("`updated_at`")
    private Long updatedAt;


}
