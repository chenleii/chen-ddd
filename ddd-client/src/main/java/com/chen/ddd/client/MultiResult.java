package com.chen.ddd.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MultiResult<T> extends BaseResult {

    /**
     * 符合条件的记录数
     */
    private Long total;

    /**
     * 数据集合
     */
    private List<T> dataList;

    public MultiResult(Long total, List<T> dataList) {
        super(true);
        this.total = total;
        this.dataList = dataList;
    }

    public MultiResult(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public static <T> MultiResult<T> success(Long total, List<T> dataList) {
        return new MultiResult<>(total, dataList);
    }

    public static <T> MultiResult<T> success(List<T> moduleList) {
        int size = moduleList == null ? 0 : moduleList.size();
        return success((long) size, moduleList);
    }

    public static MultiResult<Void> fail(String errorCode, String errorMsg) {
        return new MultiResult<>(errorCode, errorMsg);
    }
}
