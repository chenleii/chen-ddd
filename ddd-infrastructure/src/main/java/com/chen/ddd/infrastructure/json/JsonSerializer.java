package com.chen.ddd.infrastructure.json;

import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
public interface JsonSerializer {

    /**
     * 对象序列化为json字符串
     *
     * @param target
     * @return
     */
    String toJsonString(Object target);

    /**
     * 从json字符串反序列化为对象
     *
     * @param jsonStr
     * @param valueType
     * @param <T>
     * @return
     */
    <T> T toObject(String jsonStr, Class<T> valueType);

    /**
     * 反序列化生成对象列表
     *
     * @param jsonStr
     * @param valueType
     * @param <T>
     * @return
     */
    <T> List<T> toObjectList(String jsonStr, Class<T> valueType);
}
