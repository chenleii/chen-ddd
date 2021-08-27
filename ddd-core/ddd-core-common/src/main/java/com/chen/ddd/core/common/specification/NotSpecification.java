package com.chen.ddd.core.common.specification;

/**
 * NOT规则
 *
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
public class NotSpecification<T> extends AbstractSpecification<T> {

    private Specification<T> spec1;

    /**
     * Create a new NOT specification based on another spec.
     *
     * @param spec1 Specification instance to not.
     */
    public NotSpecification(final Specification<T> spec1) {
        this.spec1 = spec1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T t) {
        return !spec1.isSatisfiedBy(t);
    }
}
