package com.chen.ddd.interfaces.http.dto;

import com.chen.ddd.core.common.objectmapper.SourceToTargetMapper;
import com.chen.ddd.core.common.page.Pagination;
import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/30 16:17
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@ApiModel("分页输出数据")
public class PageOutputDTO<T extends DTO> implements DTO {

    @ApiModelProperty("当前页数")
    private Long pageIndex;

    @ApiModelProperty("每页条数")
    private Long pageSize;

    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("数据列表")
    private List<T> list;


    public static <RESULT, T extends DTO> PageOutputDTO<T> of(Pagination<RESULT> pagination, SourceToTargetMapper<RESULT, T> mapper) {
        if (Objects.isNull(pagination)) {
            return PageOutputDTO.<T>builder()
                    .pageIndex(1L)
                    .pageSize(10L)
                    .total(0L)
                    .list(Collections.emptyList())
                    .build();
        }
        Preconditions.checkNotNull(mapper);

        return PageOutputDTO.<T>builder()
                .pageIndex(pagination.getPageIndex())
                .pageSize(pagination.getPageSize())
                .total(pagination.getTotal())
                .list(mapper.sourceListToTargetList(pagination.getList()))
                .build();
    }


    /**
     * 分页数据遍历
     *
     * @param action 映射方法
     * @return 映射后的分页对象
     */
    public  PageOutputDTO<T> peek(Consumer<? super T> action) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(action);
        }
        return this;
    }
}
