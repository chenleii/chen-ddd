package com.chen.ddd.infrastructure.port.inventory;

import com.chen.ddd.core.order.port.inventory.InventoryPort;
import com.chen.ddd.core.order.port.inventory.Reduce;
import jdk.jfr.Name;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import javax.validation.constraints.Size;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 18:38
 */
@Named
@Slf4j
public class InventoryPortImpl implements InventoryPort {
    @Override
    public boolean reduceInventory(Reduce reduce) {
        //省略调用外部服务

        return true;
    }
}
