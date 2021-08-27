package com.chen.ddd.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SingleResult<T> extends BaseResult {

    /**
     * 数据对象
     */
    private T data;

    public SingleResult(T data) {
        super(true);
        this.data = data;
    }

    public SingleResult(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public static <T> SingleResult<T> success(T data) {
        return new SingleResult<>(data);
    }

    public static <T> SingleResult<T> fail(String errorCode, String errorMsg) {
        return new SingleResult<>(errorCode, errorMsg);
    }
}
