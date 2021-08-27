package com.chen.ddd.interfaces.http.result;


import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 响应封装类
 *
 * @author cl
 * @version 1.0
 * @since 2020/11/23 00:07
 */
@Builder
@Getter
@ApiModel("响应结果包装")
public class R<T> {

    private static final String SUCCESS_CODE = "200";
    private static final String FAIL_CODE = "400";
    public static final String NOT_LOGIN_FAIL_CODE = "401";
    public static final String ACCOUNT_UNINITIALIZED_FAIL_CODE = "4011";
    private static final String ERROR_CODE = "500";
    private static final String DEFAULT_SUCCESS_MSG = "成功";
    /**
     * 响应结果编码
     */
    private final String code;
    /**
     * 响应消息
     */
    private final String msg;
    /**
     * 响应数据
     */
    private final T data;

    /**
     * csrf标识
     */
    @Setter
    private String csrfToken;

    public static <T> R<T> success(T data) {
        return success(DEFAULT_SUCCESS_MSG, data);
    }

    /**
     * 成功
     *
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> success(String msg, T data) {
        return R.<T>builder()
                .code(SUCCESS_CODE)
                .msg(msg)
                .data(data)
                .build();
    }

    public static <T> R<T> fail(String msg) {
        return fail(msg, null);
    }

    /**
     * 失败
     *
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> fail(String msg, T data) {
        return fail(FAIL_CODE, msg, data);
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> fail(String code, String msg, T data) {
        return R.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }


    public static <T> R<T> error(String msg) {
        return error(msg, null);
    }

    /**
     * 失败
     *
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> error(String msg, T data) {
        return R.<T>builder()
                .code(ERROR_CODE)
                .msg(msg)
                .data(data)
                .build();
    }


}
