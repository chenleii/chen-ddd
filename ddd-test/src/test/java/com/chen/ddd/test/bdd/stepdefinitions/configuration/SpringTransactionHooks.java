package com.chen.ddd.test.bdd.stepdefinitions.configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author chen
 * @since 2020/11/15 2:25.
 */
public class SpringTransactionHooks implements BeanFactoryAware {

    private BeanFactory beanFactory;
    private TransactionStatus transactionStatus;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Before(value = "@transaction", order = 100)
    public void startTransaction() {
        transactionStatus = obtainPlatformTransactionManager()
                .getTransaction(new DefaultTransactionDefinition());
    }

    public PlatformTransactionManager obtainPlatformTransactionManager() {
        return beanFactory.getBean(PlatformTransactionManager.class);
    }

    @After(value = "@transaction", order = 100)
    public void rollBackTransaction() {
        obtainPlatformTransactionManager()
                .rollback(transactionStatus);
    }

}