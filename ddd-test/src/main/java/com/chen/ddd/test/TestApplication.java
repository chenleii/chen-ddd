package com.chen.ddd.test;

import com.chen.ddd.core.common.mq.Message;
import com.chen.ddd.core.common.mq.MessageQueuePublisher;
import com.chen.ddd.test.configuration.redis.EnableEmbeddedRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author chen
 * @since 2021/3/2 21:33.
 */
@EnableEmbeddedRedis
@SpringBootApplication(
        scanBasePackages = {
                "com.chen.ddd.core",
                "com.chen.ddd.configuration",
                "com.chen.ddd.infrastructure",
                "com.chen.ddd.interfaces.http",
        }
)
public class TestApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }


    @Primary
    @Bean
    public MessageQueuePublisher messageQueuePublisher() {
        return new MessageQueuePublisher() {
            @Override
            public void publish(String topic, String tag, Message message) {


            }
        };
    }
}