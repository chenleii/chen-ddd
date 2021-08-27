package com.chen.ddd.core.common.specification;

/**
 * AND 规则
 *
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
public class AndSpecification<T> extends AbstractSpecification<T> {

    private Specification<T> spec1;
    private Specification<T> spec2;

    /**
     * Create a new AND specification based on two other spec.
     *
     * @param spec1 Specification one.
     * @param spec2 Specification two.
     */
    public AndSpecification(final Specification<T> spec1, final Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(final T t) {
        return spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t);
    }

    @Override
    public String getErrors() {
        return spec1.getErrors() + "," + spec2.getErrors();
    }
}
