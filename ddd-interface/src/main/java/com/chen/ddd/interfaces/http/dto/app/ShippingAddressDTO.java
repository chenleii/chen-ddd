package com.chen.ddd.interfaces.http.dto.app;

import com.chen.ddd.interfaces.http.dto.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "订单收货地址")
public class ShippingAddressDTO implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    @Schema(name = "地址")
    private Address address;

    /**
     * 联系人信息
     */
    @ApiModelProperty("联系人信息")
    @Schema(name = "联系人信息")
    private ContactInfo contactInfo;


    @Getter
    @Setter
    @ToString
    @ApiModel("地址")
    @Schema(name = "地址")
    public static class Address implements DTO {

        private static final long serialVersionUID = 1L;

        /**
         * 国家
         */
        @ApiModelProperty("国家")
        @Schema(name = "国家")
        private String country;
        /**
         * 省
         */
        @ApiModelProperty("省")
        @Schema(name = "省")
        private String province;
        /**
         * 市
         */
        @ApiModelProperty("市")
        @Schema(name = "市")
        private String city;
        /**
         * 区
         */
        @ApiModelProperty("区")
        @Schema(name = "区")
        private String district;
        /**
         * 街道
         */
        @ApiModelProperty("街道")
        @Schema(name = "街道")
        private String street;
        /**
         * 详细地址
         */
        @ApiModelProperty("详细地址")
        @Schema(name = "详细地址")
        private String details;


    }


    @Getter
    @Setter
    @ToString
    @ApiModel("联系人信息")
    @Schema(name = "联系人信息")
    public static class ContactInfo implements DTO {

        private static final long serialVersionUID = 1L;

        /**
         * 名称
         */
        @ApiModelProperty("名称")
        @Schema(name = "名称")
        private String name;

        /**
         * 手机号
         */
        @ApiModelProperty("手机号")
        @Schema(name = "手机号")
        private PhoneNumber phone;
    }


    @Getter
    @Setter
    @ToString
    @ApiModel("手机号码")
    @Schema(name = "手机号码")
    public static class PhoneNumber implements DTO {

        private static final long serialVersionUID = 1L;

        /**
         * 区号
         */
        @ApiModelProperty("区号")
        @Schema(name = "区号")
        private String areaCode;

        /**
         * 手机号
         */
        @ApiModelProperty("手机号")
        @Schema(name = "手机号")
        private String number;

    }
}
