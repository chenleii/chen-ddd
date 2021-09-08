package com.chen.ddd.core.order.application.queryservice;

import com.chen.ddd.core.order.domain.model.cqrs.query.OrderQuery;
import com.chen.ddd.core.order.domain.model.cqrs.result.OrderResult;
import com.chen.ddd.core.order.domain.model.repository.OrderQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.jddd.core.annotation.Service;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 09:50
 */
@Service
@Slf4j
@Named
@Validated
public class OrderQueryService {

    @Inject
    private OrderQueryRepository orderQueryRepository;

    public OrderResult query(OrderQuery query) {
        return orderQueryRepository.queryById(query.getOrderId());
    }
}
