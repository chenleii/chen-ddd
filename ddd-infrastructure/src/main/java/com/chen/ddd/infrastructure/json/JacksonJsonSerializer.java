package com.chen.ddd.infrastructure.json;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS;

/**
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
public class JacksonJsonSerializer implements JsonSerializer {

    private static final JacksonJsonSerializer INSTANCE = new JacksonJsonSerializer();

    private ObjectMapper objectMapper;

    public JacksonJsonSerializer() {
        this.objectMapper = new ObjectMapper();

        // 忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 时间转时间戳（毫秒）
        objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS,true);
        objectMapper.configure(WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,false);
        objectMapper.configure(READ_DATE_TIMESTAMPS_AS_NANOSECONDS,false);
        // jdk8配置
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());
    }

    @Override
    public String toJsonString(Object target) {
        try {
            return objectMapper.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T toObject(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> toObjectList(String jsonStr, Class<T> valueType) {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructParametricType(List.class, valueType);
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJSONString(Object target) {
        return INSTANCE.toJsonString(target);
    }

    public static <T> T toJavaObject(String jsonStr, Class<T> valueType) {
        return INSTANCE.toObject(jsonStr, valueType);
    }

    public static <T> List<T> toJavaObjectList(String jsonStr, Class<T> valueType) {
        return INSTANCE.toObjectList(jsonStr, valueType);
    }
}