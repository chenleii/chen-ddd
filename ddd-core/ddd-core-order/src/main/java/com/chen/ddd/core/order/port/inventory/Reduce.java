package com.chen.ddd.core.order.port.inventory;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 15:40
 */
@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class Reduce {

    private List<ReduceItem> itemList;

    public Reduce addItem(Long productId, Integer quantity) {
        if (itemList == null) {
            this.itemList = new ArrayList<>(8);
        }

        this.itemList.add(
                ReduceItem.of(productId, quantity)
        );

        return this;
    }

    public int size() {
        return itemList != null ? itemList.size() : 0;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor(staticName = "of")
    public static class ReduceItem {
        /**
         * 商品ID
         */
        private Long productId;
        /**
         * 扣减数量
         */
        private Integer quantity;
    }
}
