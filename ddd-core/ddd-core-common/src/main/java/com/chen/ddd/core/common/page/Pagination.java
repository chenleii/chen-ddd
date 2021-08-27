package com.chen.ddd.core.common.page;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/5
 */
@Getter
public class Pagination<T> {

    /**
     * 当前页数
     */
    private final Long pageIndex;
    /**
     * 每页条数
     */
    private final Long pageSize;

    /**
     * 总数
     */
    private final Long total;

    /**
     * 数据列表
     */
    private final List<T> list;

    public Pagination(long pageIndex, long pageSize, long total, List<T> list) {
        Preconditions.checkNotNull(list);

        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

    public static <T> Pagination<T> create(long pageIndex, long pageSize,
                                           long total, List<T> list) {
        return new Pagination<>(pageIndex, pageSize, total, list);
    }

    public static <T> Pagination<T> create(long pageIndex, long pageSize) {
        return new Pagination<>(pageIndex, pageSize, 0, Collections.emptyList());
    }

    /**
     * 分页数据映射为指定类型
     *
     * @param mapper 映射方法
     * @param <R>    映射后的类型
     * @return 映射后的分页对象
     */
    public <R> Pagination<R> map(Function<List<T>, List<R>> mapper) {
        return Pagination.create(this.getPageIndex(), this.getPageSize(),
                this.getTotal(), mapper.apply(this.list));
    }

    /**
     * 分页数据遍历
     *
     * @param action 映射方法
     * @return 映射后的分页对象
     */
    public  Pagination<T> peek(Consumer<? super T> action) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(action);
        }
        return this;
    }
}
