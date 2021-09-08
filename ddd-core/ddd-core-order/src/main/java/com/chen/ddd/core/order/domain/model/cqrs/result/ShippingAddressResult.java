package com.chen.ddd.core.order.domain.model.cqrs.result;

import com.chen.ddd.core.common.cqrs.Result;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cl
 * @version 1.0
 * @since 2021/9/7 17:34
 */
@Getter
@Setter
@ToString
public class ShippingAddressResult implements Result {

    /**
     * 地址
     */
    private Address address;

    /**
     * 联系人信息
     */
    private ContactInfo contactInfo;


    @Getter
    @Setter
    @ToString
    public static class Address {

        /**
         * 国家
         */
        private String country;
        /**
         * 省
         */
        private String province;
        /**
         * 市
         */
        private String city;
        /**
         * 区
         */
        private String district;
        /**
         * 街道
         */
        private String street;
        /**
         * 详细地址
         */
        private String details;


    }


    @Getter
    @Setter
    @ToString
    public static class ContactInfo {

        /**
         * 名称
         */
        private String name;

        /**
         * 手机号
         */
        private PhoneNumber phone;
    }


    @Getter
    @Setter
    @ToString
    public static class PhoneNumber {

        /**
         * 区号
         */
        private String areaCode;

        /**
         * 手机号
         */
        private String number;

    }
}
