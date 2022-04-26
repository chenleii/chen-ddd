package com.chen.ddd.interfaces.http.dto.app;

import com.chen.ddd.core.order.domain.model.logistics.LogisticsStatus;
import com.chen.ddd.interfaces.http.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@ApiModel("订单物流信息")
@Schema(name = "订单物流信息")
public class LogisticsInfoDTO implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * 运单号
     */
    @ApiModelProperty("运单号")
    @Schema(name = "运单号")
    private String trackingNo;

    /**
     * 物流状态
     */
    @ApiModelProperty("物流状态")
    @Schema(name = "物流状态")
    private LogisticsStatus status;
}
