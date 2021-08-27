package com.chen.ddd.infrastructure.port.dingtalk;

/**
 * 钉钉API
 */
public class ApiUrlConstant {
    /**
     * 获取可访问企业相关信息的accessToken的URL
     */
    public static final String URL_GET_CORP_TOKEN = "https://oapi.dingtalk.com/service/get_corp_token";

    /**
     * 免登code获取用户在企业内userId的接口URL
     */
    public static final String URL_CODE_GET_USER_INFO = "https://oapi.dingtalk.com/user/getuserinfo";

    /**
     * 根据userid获取用户信息接口URL
     */
    public static final String URL_GET_USER_INFO = "https://oapi.dingtalk.com/topapi/v2/user/get";

    /**
     * 获取部门信息接口URL
     */
    public static final String URL_GET_DEPARTMENT_INFO = "https://oapi.dingtalk.com/topapi/v2/department/get";

    /**
     * 获取部门子部门ID列表接口URL
     */
    public static final String URL_GET_SUB_DEPARTMENT_ID_LIST_INFO = "https://oapi.dingtalk.com/topapi/v2/department/listsubid";


    /**
     * 获取部门下级部门信息接口URL
     */
    public static final String URL_GET_DEPARTMENT_SUB_INFO = "https://oapi.dingtalk.com/topapi/v2/department/listsub";

    /**
     * 获取部门下用户ID列表接口URL
     */
    public static final String URL_GET_DEPARTMENT_USERID_LIST_INFO = "https://oapi.dingtalk.com/topapi/user/listid";

    /**
     * 获取企业授权信息接口URL
     */
    public static final String URL_GET_CORP_AUTH_INFO = "https://oapi.dingtalk.com/service/get_auth_info";


    /**
     * 发送工作通知接口URL
     */
    public static final String URL_SEND_WORK_NOTIFICATION = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";

    /**
     * 发送模板工作通知接口URL
     */
    public static final String URL_SEND_TEMPLATE_WORK_NOTIFICATION = "https://oapi.dingtalk.com/topapi/message/corpconversation/sendbytemplate";

    /**
     * 发送普通通知接口URL
     */
    public static final String URL_SEND_NOTIFICATION = "https://oapi.dingtalk.com/message/send_to_conversation";

    /**
     * 获取jsapi_ticket
     */
    public static final String URL_GET_JSAPI_TICKET = "https://oapi.dingtalk.com/get_jsapi_ticket";


}