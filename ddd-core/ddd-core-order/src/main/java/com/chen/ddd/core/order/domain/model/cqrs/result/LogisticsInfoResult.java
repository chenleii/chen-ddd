package com.chen.ddd.core.order.domain.model.cqrs.result;

import com.chen.ddd.core.common.cqrs.Result;
import com.chen.ddd.core.order.domain.model.logistics.LogisticsStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 17:35
 */
@Getter
@Setter
@ToString
public class LogisticsInfoResult implements Result {

    /**
     * 运单号
     */
    private String trackingNo;

    /**
     * 物流状态
     */
    private LogisticsStatus status;
}
