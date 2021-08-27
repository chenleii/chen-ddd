package com.chen.ddd.core.common.cqrs;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/5
 */
@SuperBuilder
@Getter
public class PageQuery implements Query {
    /**
     * 当前页数
     */
    @NotNull(message = "当前页数不能为空")
    private final Long pageIndex;
    /**
     * 每页条数
     */
    @NotNull(message = "每页大小不能为空")
    private final Long pageSize;
}
