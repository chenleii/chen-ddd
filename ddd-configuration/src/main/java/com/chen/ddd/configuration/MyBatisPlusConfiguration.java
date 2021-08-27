package com.chen.ddd.configuration;

import com.chen.ddd.infrastructure.persistence.dal.mybatisplus.sqlinjector.ChenSqlInjector;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/27 16:10
 */
@Configuration
@MapperScan("com.chen.ddd.infrastructure.persistence.dal.mybatisplus.mybatismapper")
public class MyBatisPlusConfiguration {

    /**
     * 分页插件
     */
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        innerInterceptor.setDbType(DbType.MYSQL);
        mybatisPlusInterceptor.addInnerInterceptor(innerInterceptor);
        return mybatisPlusInterceptor;
    }

    @Bean
    public CommandLineRunner mybatisPlusJacksonTypeHandlerCommandLineRunner() {
        return (args) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            // 忽略未知字段
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JacksonTypeHandler.setObjectMapper(objectMapper);
        };
    }


    /**
     * 自定义sql注入器
     */
    @Bean
    public ChenSqlInjector dddSqlInjector() {
        return new ChenSqlInjector();
    }


}
