package com.chen.ddd.infrastructure.util;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.function.Function;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/11
 */
public class Property {

    public static <T, R> String property(Function<T, R> function) {
        SerializedLambda serializedLambda = LambdaUtils.resolve(function);
        String methodName = serializedLambda.getImplMethodName();
        return methodNameToPropertyName(methodName);
    }

    private static String methodNameToPropertyName(String methodName) {
        if (methodName.startsWith("is")) {
            methodName = methodName.substring(2);
        } else {
            if (!methodName.startsWith("get") && !methodName.startsWith("set")) {
                throw new RuntimeException("Error parsing property name '" + methodName + "'.  Didn't start with 'is', 'get' or 'set'.");
            }
            methodName = methodName.substring(3);
        }

        if (methodName.length() == 1 || methodName.length() > 1 && !Character.isUpperCase(methodName.charAt(1))) {
            methodName = methodName.substring(0, 1).toLowerCase(Locale.ENGLISH) + methodName.substring(1);
        }

        return methodName;
    }
}
