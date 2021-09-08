package com.chen.ddd.interfaces.http.controller.app;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/23 15:09
 */
public abstract class AbstractAppController implements AppController {

    @Inject
    private HttpServletRequest request;
    @Inject
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * 获取登录态下买家ID
     *
     * @return 买家ID
     */
    protected Long getCurrentBuyerId() {
        return 1L;
    }
}
