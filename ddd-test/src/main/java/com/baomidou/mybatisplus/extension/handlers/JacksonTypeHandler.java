package com.baomidou.mybatisplus.extension.handlers;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;

/**
 * Jackson 实现 JSON 字段类型处理器
 *
 * @author hubin
 * @since 2019-08-25
 */
@Slf4j
@MappedTypes({Object.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JacksonTypeHandler extends AbstractJsonTypeHandler<Object> {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private final Class<?> type;

    public JacksonTypeHandler(Class<?> type) {
        if (log.isTraceEnabled()) {
            log.trace("JacksonTypeHandler(" + type + ")");
        }
        Assert.notNull(type, "Type argument cannot be null");
        this.type = type;
    }

    @Override
    protected Object parse(String json) {
        try {
            // 仅测试模块使用
            // 兼容h2返回的json格式
            json = StringUtils.removeStart(json, "\"");
            json = StringUtils.removeEnd(json, "\"");
            json = StringEscapeUtils.unescapeJson(json);
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "ObjectMapper should not be null");
        JacksonTypeHandler.objectMapper = objectMapper;
    }
}
