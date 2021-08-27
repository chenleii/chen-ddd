package com.chen.ddd.infrastructure.util;

import java.lang.invoke.SerializedLambda;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Lambda 解析工具类
 *
 * @author chen
 * @since 2020/4/24 12:04.
 */
public class LambdaUtils {

    /**
     * SerializedLambda 反序列化缓存
     */
    private static final Map<Class<?>, WeakReference<SerializedLambda>> FUNC_CACHE = new ConcurrentHashMap<>();


    /**
     * 转换 lambda 表达式
     * <p>
     * 该方法只能序列化 lambda 表达式，不能序列化接口实现或者正常非 lambda 写法的对象
     *
     * @param lambda lambda对象
     * @return 返回解析后的 SerializedLambda
     */
    private static SerializedLambda resolveSerializedLambda(Object lambda) {
        if (!lambda.getClass().isSynthetic()) {
            throw new IllegalArgumentException("is not lambda class.");
        }
        try {
            final Method writeReplace = lambda.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);
            return (SerializedLambda) writeReplace.invoke(lambda);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // NoSuchMethodException异常大概率是类加载器问题，例如使用了spring热部署。
            throw new RuntimeException("This is impossible to happen.", e);
        }
    }


    /**
     * 解析 lambda 表达式,
     * 该缓存可能会在任意不定的时间被清除
     *
     * @param func 需要解析的 lambda 对象
     * @return 返回解析后的结果
     */
    public static SerializedLambda resolve(Object func) {
        Class<?> clazz = func.getClass();

        return Optional.ofNullable(FUNC_CACHE.get(clazz))
                .map(WeakReference::get)
                .orElseGet(() -> {
                    SerializedLambda lambda = resolveSerializedLambda(func);
                    FUNC_CACHE.put(clazz, new WeakReference<>(lambda));
                    return lambda;
                });
    }

}
