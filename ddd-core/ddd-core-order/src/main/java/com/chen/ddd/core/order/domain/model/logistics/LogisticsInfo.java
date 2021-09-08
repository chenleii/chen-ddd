package com.chen.ddd.core.order.domain.model.logistics;

import lombok.*;
import org.jddd.core.annotation.ValueObject;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/6 17:32
 */
@ValueObject
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor(staticName = "of")
@Builder
public class LogisticsInfo {

    /**
     * 运单号
     */
    private String trackingNo;

    /**
     * 物流状态
     */
    private LogisticsStatus status;

    /**
     * 空的物流信息
     *
     * @return 物流信息
     */
    public static LogisticsInfo empty() {
        return LogisticsInfo.of(null, LogisticsStatus.NONE);
    }

    /**
     * 变更
     *
     * @param mailNo 运单号
     * @param status 状态
     * @return 新的物流信息
     */
    public LogisticsInfo update(String mailNo, LogisticsStatus status) {
        return LogisticsInfo.of(mailNo, status);
    }

    /**
     * 变更状态
     *
     * @param status 状态
     * @return 新的物流信息
     */
    public LogisticsInfo updateStatus(LogisticsStatus status) {
        return LogisticsInfo.of(getTrackingNo(), status);
    }


}
