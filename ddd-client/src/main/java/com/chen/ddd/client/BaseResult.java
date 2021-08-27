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
public abstract class BaseResult {

    /**
     * 是否成功
     */
    protected boolean success;

    /**
     * 错误码
     */
    protected String errorCode;

    /**
     * 错误信息
     */
    protected String errorMsg;

    public BaseResult(boolean success) {
        this.success = success;
    }

    public BaseResult(String errorCode, String errorMsg) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 是否成功
     *
     * @return 是/否
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 是否未成功
     *
     * @return 是/否
     */
    public boolean isNotSuccess() {
        return !success;
    }
}
