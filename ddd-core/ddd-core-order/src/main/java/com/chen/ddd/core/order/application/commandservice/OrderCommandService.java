package com.chen.ddd.core.order.application.commandservice;

import com.chen.ddd.core.order.domain.model.Order;
import com.chen.ddd.core.order.domain.model.OrderId;
import com.chen.ddd.core.order.domain.model.cqrs.command.CreatePrepaidOrderCommand;
import com.chen.ddd.core.order.domain.model.cqrs.command.FinishOrderCommand;
import com.chen.ddd.core.order.domain.model.cqrs.command.PayOrderCommand;
import com.chen.ddd.core.order.domain.model.cqrs.command.ShipOrderCommand;
import com.chen.ddd.core.order.domain.model.exception.OrderReduceInventoryException;
import com.chen.ddd.core.order.domain.model.factory.OrderFactory;
import com.chen.ddd.core.order.domain.model.repository.OrderRepository;
import com.chen.ddd.core.order.domain.model.service.OrderService;
import com.chen.ddd.core.order.port.inventory.InventoryPort;
import com.chen.ddd.core.order.port.inventory.Reduce;
import com.chen.ddd.core.order.port.product.ProductPort;
import lombok.extern.slf4j.Slf4j;
import org.jddd.core.annotation.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 15:57
 */
@Service
@Slf4j
@Named
@Validated
public class OrderCommandService {

    @Inject
    private ProductPort productPort;
    @Inject
    private InventoryPort inventoryPort;

    @Inject
    private OrderService orderService;
    @Inject
    private OrderFactory orderFactory;
    @Inject
    private OrderRepository orderRepository;

    /**
     * 创建预支付订单
     *
     * @param command 命令
     * @return 订单ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long createPrepaidOrder(@Valid CreatePrepaidOrderCommand command) {
        checkInventory(command);

        final Order order = orderFactory.create(command);
        orderRepository.save(order);

        return order.getId().getId();
    }

    /**
     * 检查库存
     *
     * @param command 命令
     */
    private void checkInventory(CreatePrepaidOrderCommand command) {
        final Reduce reduce = Reduce.of();
        for (CreatePrepaidOrderCommand.Item item : command.getItemList()) {
            reduce.addItem(item.getProductId(), item.getQuantity());
        }
        final boolean reduceInventory = inventoryPort.reduceInventory(reduce);
        if (!reduceInventory) {
            throw new OrderReduceInventoryException("order reduce inventory failure.");
        }
    }

    /**
     * 支付订单
     *
     * @param command 命令
     */
    @Transactional(rollbackFor = Exception.class)
    public void payOrder(PayOrderCommand command) {
        final OrderId orderId = OrderId.of(command.getOrderId());
        final Order order = orderRepository.getLockByIdNotExistsThrowException(orderId);

        orderService.pay(order);
        orderRepository.save(order);
    }

    /**
     * 发货订单
     *
     * @param command 命令
     */
    @Transactional(rollbackFor = Exception.class)
    public void shipOrder(ShipOrderCommand command) {
        final OrderId orderId = OrderId.of(command.getOrderId());
        final Order order = orderRepository.getByIdNotExistsThrowException(orderId);

        order.ship(command.getMailNo());
        orderRepository.save(order);
    }

    /**
     * 完结订单
     *
     * @param command 命令
     */
    @Transactional(rollbackFor = Exception.class)
    public void finishOrder(FinishOrderCommand command) {
        final OrderId orderId = OrderId.of(command.getOrderId());
        final Order order = orderRepository.getByIdNotExistsThrowException(orderId);

        order.finish();
        orderRepository.save(order);
    }


}
