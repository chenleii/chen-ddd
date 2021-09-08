package com.chen.ddd.infrastructure.persistence.dal.mybatisplus.sqlinjector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.chen.ddd.core.order.domain.model.ProductSnapshot;
import com.chen.ddd.infrastructure.json.JsonSerializers;
import org.javamoney.moneta.Money;

import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/3 22:14
 */
public class CustomSqlInjector extends DefaultSqlInjector {


    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new InsertIgnore());
        methodList.add(new InsertOnDuplicateKeyUpdate());
        return methodList;
    }


    public static void main(String[] args) {
        final ProductSnapshot productSnapshot = ProductSnapshot.builder().unitPrice(Money.of(1, "CNY")).build();
        final String s = JsonSerializers.toJsonString(productSnapshot);
        System.out.println(s);
    }
}
