package com.chen.ddd.core.common.cqrs;

import lombok.*;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Set;

/**
 * 单位时间统计结果
 *
 * @author cl
 * @version 1.0
 * @since 2021/7/7 10:12
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor(staticName = "of")
public class UnitStatisticsResult implements Result {

    /**
     * 时间单位
     */
    private ChronoUnit unit = ChronoUnit.DAYS;

    /**
     * 项目列表
     */
    private Set<Item> itemList;

    @Setter
    @Getter
    @ToString(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode(of = "segment")
    public static class Item {
        /**
         * 部分
         * <p>
         * 每天
         * 每小时
         * 每分钟
         */
        private String segment;

        /**
         * 该部分对应的值
         */
        private BigDecimal value;
    }

}
