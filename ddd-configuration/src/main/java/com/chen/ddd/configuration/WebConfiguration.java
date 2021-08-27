package com.chen.ddd.configuration;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Instant;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;


/**
 * @author cl
 * @version 1.0
 * @since 2020/11/5
 */
// @EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/swagger-resources/**").addResourceLocations("classpath:/META-INF/swagger-resources/webjars/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Instant>() {
            @Override
            public Instant convert(String s) {
                if (StringUtils.isBlank(s)) {
                    return null;
                }
                return Instant.ofEpochSecond(Long.parseLong(s));
            }
        });
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return (builder) -> {
            // 时间转纳秒（小数点前秒，小数点后纳秒）
            builder.featuresToEnable(WRITE_DATES_AS_TIMESTAMPS);
            // 时间转毫秒
            // builder.featuresToDisable(WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
            // builder.featuresToDisable(READ_DATE_TIMESTAMPS_AS_NANOSECONDS);

            builder.serializerByType(Long.class, new ToStringSerializer());
        };
    }
}
