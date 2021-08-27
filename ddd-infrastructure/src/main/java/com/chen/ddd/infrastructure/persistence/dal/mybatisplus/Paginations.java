package com.chen.ddd.infrastructure.persistence.dal.mybatisplus;

import com.chen.ddd.core.common.cqrs.PageQuery;
import com.chen.ddd.core.common.objectmapper.SourceToTargetMapper;
import com.chen.ddd.core.common.page.Pagination;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/2 16:10
 */
public class Paginations {

    public static <DO, RESULT> Pagination<RESULT> create(PageQuery pageQuery, Page<DO> page, SourceToTargetMapper<DO, RESULT> mapper) {
        if (Objects.isNull(page)) {
            return Pagination.create(pageQuery.getPageIndex(), pageQuery.getPageSize());
        }

        return Pagination.create(page.getCurrent(), page.getSize(), page.getTotal(), mapper.sourceListToTargetList(page.getRecords()));
    }
}
