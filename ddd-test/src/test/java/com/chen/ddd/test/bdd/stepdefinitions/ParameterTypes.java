package com.chen.ddd.test.bdd.stepdefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DataTableType;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;

import java.lang.reflect.Type;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/15 14:34
 */
public class ParameterTypes {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(Object fromValue, Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }

    @DataTableType(replaceWithEmptyString = "null")
    public String defaultString(String cell) {
        return null;
    }

}
