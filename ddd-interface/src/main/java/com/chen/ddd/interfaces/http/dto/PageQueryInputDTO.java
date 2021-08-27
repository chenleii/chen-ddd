package com.chen.ddd.interfaces.http.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/30 16:17
 */
@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("分页传入数据")
public class PageQueryInputDTO implements DTO {

    @ApiModelProperty("当前页数")
    private Long pageIndex;

    @ApiModelProperty("每页条数")
    private Long pageSize;
}
