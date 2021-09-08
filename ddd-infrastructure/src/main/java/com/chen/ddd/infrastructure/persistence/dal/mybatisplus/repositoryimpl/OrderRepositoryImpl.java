package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.repositoryimpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chen.ddd.core.order.domain.model.Order;
import com.chen.ddd.core.order.domain.model.OrderId;
import com.chen.ddd.core.order.domain.model.OrderItemId;
import com.chen.ddd.core.order.domain.model.cqrs.result.OrderResult;
import com.chen.ddd.core.order.domain.model.repository.OrderQueryRepository;
import com.chen.ddd.core.order.domain.model.repository.OrderRepository;
import com.chen.ddd.infrastructure.idgenerator.IdGenerators;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.dataobject.OrderDO;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.dataobject.OrderItemDO;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.domapper.OrderDOMapper;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.mybatismapper.OrderItemMapper;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.mybatismapper.OrderMapper;
import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.resultmapper.OrderResultMapper;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 16:41
 */
@Named
@Slf4j
public class OrderRepositoryImpl implements OrderRepository, OrderQueryRepository {

    @Inject
    private OrderMapper orderMapper;
    @Inject
    private OrderItemMapper orderItemMapper;


    @Override
    public OrderId generateId() {
        return OrderId.of(IdGenerators.generateId());
    }

    @Override
    public OrderItemId generateOrderItemId() {
        return OrderItemId.of(IdGenerators.generateId());
    }


    private void assembly(OrderDO orderDO) {
        if (Objects.isNull(orderDO)) {
            return;
        }

        final List<OrderItemDO> orderItemDOList = orderItemMapper.selectList(
                Wrappers.<OrderItemDO>lambdaQuery()
                        .eq(OrderItemDO::getOrderId, orderDO.getId())
        );

        orderDO.setItemList(orderItemDOList);

    }

    @Override
    public Order getById(OrderId orderId) {
        final Long id = orderId.getId();
        final OrderDO orderDO = orderMapper.selectById(id);
        assembly(orderDO);
        return OrderDOMapper.MAPPER.targetToSource(orderDO);
    }

    @Override
    public Order getLockById(OrderId orderId) {
        final Long id = orderId.getId();
        final OrderDO orderDO = orderMapper.selectOneForUpdate(
                Wrappers.<OrderDO>lambdaQuery()
                        .eq(OrderDO::getId, id)
        );
        assembly(orderDO);
        return OrderDOMapper.MAPPER.targetToSource(orderDO);
    }

    @Override
    public void save(Order order) {
        final OrderDO orderDO = OrderDOMapper.MAPPER.sourceToTarget(order);
        orderMapper.insertOnDuplicateKeyUpdate(orderDO);
        orderItemMapper.insertOnDuplicateKeyUpdateBatch(orderDO.getItemList());
    }


    @Override
    public OrderResult queryById(Long orderId) {
        final OrderDO orderDO = orderMapper.selectById(orderId);
        assembly(orderDO);
        return OrderResultMapper.MAPPER.sourceToTarget(orderDO);
    }

}
