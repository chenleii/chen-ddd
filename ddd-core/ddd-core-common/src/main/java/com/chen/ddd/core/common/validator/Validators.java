package com.chen.ddd.core.common.validator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/13 19:51
 */
@Named
public class Validators {

    /**
     * 验证器
     */
    private static Validator VALIDATOR;

    @Inject
    public Validators(Validator validator) {
        VALIDATOR = validator;
    }

    public static  <T> Set<ConstraintViolation<T>> validate(T o, Class<?>... groups) {
        return VALIDATOR.validate(o, groups);
    }
}
