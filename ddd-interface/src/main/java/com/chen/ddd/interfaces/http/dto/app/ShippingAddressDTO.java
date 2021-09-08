package com.chen.ddd.interfaces.http.dto.app;

import com.chen.ddd.interfaces.http.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("订单收货地址")
public class ShippingAddressDTO implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private Address address;

    /**
     * 联系人信息
     */
    @ApiModelProperty("联系人信息")
    private ContactInfo contactInfo;


    @Getter
    @Setter
    @ToString
    public static class Address implements DTO {

        private static final long serialVersionUID = 1L;

        /**
         * 国家
         */
        @ApiModelProperty("国家")
        private String country;
        /**
         * 省
         */
        @ApiModelProperty("省")
        private String province;
        /**
         * 市
         */
        @ApiModelProperty("市")
        private String city;
        /**
         * 区
         */
        @ApiModelProperty("区")
        private String district;
        /**
         * 街道
         */
        @ApiModelProperty("街道")
        private String street;
        /**
         * 详细地址
         */
        @ApiModelProperty("详细地址")
        private String details;


    }


    @Getter
    @Setter
    @ToString
    public static class ContactInfo implements DTO {

        private static final long serialVersionUID = 1L;

        /**
         * 名称
         */
        @ApiModelProperty("名称")
        private String name;

        /**
         * 手机号
         */
        @ApiModelProperty("手机号")
        private PhoneNumber phone;
    }


    @Getter
    @Setter
    @ToString
    public static class PhoneNumber implements DTO {

        private static final long serialVersionUID = 1L;

        /**
         * 区号
         */
        @ApiModelProperty("区号")
        private String areaCode;

        /**
         * 手机号
         */
        @ApiModelProperty("手机号")
        private String number;

    }
}
