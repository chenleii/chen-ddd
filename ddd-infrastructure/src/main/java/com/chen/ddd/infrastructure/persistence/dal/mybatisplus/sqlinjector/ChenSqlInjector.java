package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.sqlinjector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/3 22:14
 */
public class ChenSqlInjector extends DefaultSqlInjector {


    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new InsertIgnore());
        return methodList;
    }
}
