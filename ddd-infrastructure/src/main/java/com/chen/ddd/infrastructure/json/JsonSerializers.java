package com.chen.ddd.infrastructure.json;

import java.util.List;

/**
 * @author cl
 * @date 2020/10/30
 */
public class JsonSerializers {

    private static final JsonSerializer INSTANCE = new JacksonJsonSerializer();

    public static String toJsonString(Object target) {
        return INSTANCE.toJsonString(target);
    }

    public static <T> T toJavaObject(String jsonStr, Class<T> valueType) {
        return INSTANCE.toObject(jsonStr, valueType);
    }

    public static <T> List<T> toJavaObjectList(String jsonStr, Class<T> valueType) {
        return INSTANCE.toObjectList(jsonStr, valueType);
    }
}