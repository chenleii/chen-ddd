package com.chen.ddd.core.order.domain.model.cqrs.command;

import com.chen.ddd.core.common.cqrs.Command;
import com.chen.ddd.core.order.domain.model.shipping.ShippingAddress;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 15:59
 */
@Getter
@ToString
@Builder
public class CreatePrepaidOrderCommand implements Command {

    /**
     * 买家ID
     */
    @NotNull
    private final Long buyerId;

    /**
     * 订单项目列表
     */
    @NotEmpty
    @Valid
    private final List<Item> itemList;

    /**
     * 收货地址
     */
    @NotNull
    private final ShippingAddress shippingAddress;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor(staticName = "of")
    public static class Item {
        /**
         * 商品ID
         */
        @NotNull
        private final Long productId;
        /**
         * 扣减数量
         */
        @NotNull
        @Min(value = 1)
        private final Integer quantity;
    }
}
