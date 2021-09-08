package com.chen.ddd.core.order.port.inventory;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 15:31
 */
public interface InventoryPort {

    /**
     * 扣减库存
     *
     * @param reduce 扣减库存信息
     * @return 成功/失败
     */
    boolean reduceInventory(Reduce reduce);


}
