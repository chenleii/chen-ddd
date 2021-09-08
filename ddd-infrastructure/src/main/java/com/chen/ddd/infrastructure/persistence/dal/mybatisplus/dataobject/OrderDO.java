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
import java.util.List;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author chen
 * @since 2021-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "`order`" , autoResultMap = true)
public class OrderDO implements Serializable {


    /**
     * 订单ID
     */
    @TableId(value = "id" , type = IdType.INPUT)
    private Long id;

    /**
     * 买家ID
     */
    @TableField("`buyer_id`")
    private Long buyerId;

    /**
     * 订单总价货币
     */
    @TableField("`total_price_currency`")
    private String totalPriceCurrency;

    /**
     * 订单总价
     */
    @TableField("`total_price`")
    private BigDecimal totalPrice;

    /**
     * 订单状态
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 收货地址
     */
    @TableField(value = "`shipping_address`" , typeHandler = JacksonTypeHandler.class)
    private ShippingAddressDO shippingAddress;

    /**
     * 物流快递单号
     */
    @TableField("`logistics_tracking_no`")
    private String logisticsTrackingNo;

    /**
     * 物流状态
     */
    @TableField("`logistics_status`")
    private Integer logisticsStatus;

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


    @TableField(exist = false)
    private List<OrderItemDO> itemList;
}
