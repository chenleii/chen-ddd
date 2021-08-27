package com.chen.ddd.core.common.specification;

/**
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
public class SpecificationUnSatisfiedException extends IllegalArgumentException {

    public SpecificationUnSatisfiedException(String errorMsg) {
        super(errorMsg);
    }

}
