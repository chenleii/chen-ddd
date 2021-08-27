package com.chen.ddd.core.common.specification;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.HashSet;
import java.util.Set;

/**
 * Specification抽象基类，默认实现了and, or和not操作
 *
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    protected Set<String> errors = new HashSet<>();

    @Override
    public void assertSatisfiedBy(T t) throws SpecificationUnSatisfiedException {
        if (!isSatisfiedBy(t)) {
            throw new SpecificationUnSatisfiedException(getErrors());
        }
    }

    @Override
    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }

    @Override
    public Specification<T> or(final Specification<T> specification) {
        return new OrSpecification<T>(this, specification);
    }

    @Override
    public Specification<T> not() {
        return new NotSpecification<T>(this);
    }

    @Override
    public String getErrors() {
        return String.join(",", errors);
    }

    public boolean isHasError() {
        return CollectionUtils.isNotEmpty(errors);
    }

    protected void addError(String format, Object... params) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, params);
        String message = ft.getMessage();
        errors.add(message);
    }

    /**
     * 根据条件添加错误信息
     *
     * @param condition   条件
     * @param errorFormat 错误信息
     * @param params      错误参数
     * @return 是/否添加
     */
    protected boolean addError(boolean condition, String errorFormat, Object... params) {
        if (!condition) {
            addError(errorFormat, params);
            return true;
        }

        return false;
    }
}
