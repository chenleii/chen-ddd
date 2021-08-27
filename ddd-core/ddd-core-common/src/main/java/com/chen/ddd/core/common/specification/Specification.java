package com.chen.ddd.core.common.specification;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务规则
 *
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
public interface Specification<T> {
    /**
     * Check if {@code t} is satisfied by the specification.
     *
     * @param t Object to test.
     * @return {@code true} if {@code t} satisfies the specification.
     */
    boolean isSatisfiedBy(T t);

    /**
     * Check if {@code t} is satisfied by the specification.
     *
     * @param t Object to test.
     * @throws SpecificationUnSatisfiedException if {@code t} not satisfies the specification.
     */
    void assertSatisfiedBy(T t) throws SpecificationUnSatisfiedException;

    /**
     * Create a new specification that is the AND operation of {@code this} specification and another specification.
     *
     * @param specification Specification to AND.
     * @return A new specification.
     */
    Specification<T> and(Specification<T> specification);

    /**
     * Create a new specification that is the OR operation of {@code this} specification and another specification.
     *
     * @param specification Specification to OR.
     * @return A new specification.
     */
    Specification<T> or(Specification<T> specification);

    /**
     * Create a new specification that is the NOT operation of {@code this} specification.
     *
     * @return A new specification.
     */
    Specification<T> not();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    default String getErrors() {
        return StringUtils.EMPTY;
    }
}
