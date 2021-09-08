package com.chen.ddd.start;

import com.chen.ddd.configuration.test.redis.EnableEmbeddedRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Spring Boot应用的入口类
 *
 * @author chengxu
 */
@EnableAsync
@EnableEmbeddedRedis
@SpringBootApplication(
        scanBasePackages = {"com.chen.ddd"}
)
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
