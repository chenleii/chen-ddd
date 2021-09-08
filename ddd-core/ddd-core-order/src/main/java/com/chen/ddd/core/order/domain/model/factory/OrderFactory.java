package com.chen.ddd.core.order.domain.model.factory;

import com.chen.ddd.core.order.domain.model.*;
import com.chen.ddd.core.order.domain.model.cqrs.command.CreatePrepaidOrderCommand;
import com.chen.ddd.core.order.domain.model.repository.OrderRepository;
import com.chen.ddd.core.order.port.inventory.InventoryPort;
import com.chen.ddd.core.order.port.product.ProductInfo;
import com.chen.ddd.core.order.port.product.ProductPort;
import lombok.extern.slf4j.Slf4j;
import org.jddd.core.annotation.Factory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 17:36
 */
@Named
@Slf4j
@Factory
public class OrderFactory {

    @Inject
    private ProductPort productPort;
    @Inject
    private InventoryPort inventoryPort;

    @Inject
    private OrderRepository orderRepository;

    public Order create(CreatePrepaidOrderCommand command) {
        final OrderId orderId = orderRepository.generateId();
        final List<OrderItem> orderItemList = buildOrderItemList(orderId, command);

        return Order.create(
                orderId,
                command.getBuyerId(),
                orderItemList,
                command.getShippingAddress()
        );
    }

    private List<OrderItem> buildOrderItemList(OrderId orderId, CreatePrepaidOrderCommand command) {
        final ArrayList<OrderItem> orderItemList = new ArrayList<>(command.getItemList().size());
        for (CreatePrepaidOrderCommand.Item item : command.getItemList()) {
            final OrderItemId orderItemId = orderRepository.generateOrderItemId();
            final Long productId = item.getProductId();
            final Integer quantity = item.getQuantity();
            final ProductInfo productInfo = productPort.getProductInfo(productId);

            final OrderItem orderItem = OrderItem.create(
                    orderItemId,
                    orderId,
                    command.getBuyerId(),
                    productId,
                    productInfo.getTitle(),
                    quantity,
                    productInfo.getUnitPrice(),
                    buildProductSnapshot(productInfo)
            );
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    public ProductSnapshot buildProductSnapshot(ProductInfo productInfo) {
        if (Objects.isNull(productInfo)) {
            return null;
        }
        return ProductSnapshot.builder()
                .productId(productInfo.getProductId())
                .title(productInfo.getTitle())
                .details(productInfo.getDetails())
                .unitPrice(productInfo.getUnitPrice())
                .build();
    }

}
