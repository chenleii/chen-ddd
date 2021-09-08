package com.chen.ddd.core.order.domain.model.cqrs.command;

import com.chen.ddd.core.common.cqrs.Command;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 15:59
 */
@Getter
@ToString
@Builder
public class FinishOrderCommand implements Command {

    /**
     * 订单ID
     */
    @NotNull
    private final Long orderId;

}
