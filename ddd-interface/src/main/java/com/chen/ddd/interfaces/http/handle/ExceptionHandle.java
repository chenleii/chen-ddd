package com.chen.ddd.interfaces.http.handle;


import com.chen.ddd.core.common.exception.DomainRuntimeException;
import com.chen.ddd.core.common.exception.NotExistException;
import com.chen.ddd.interfaces.http.exception.NotLoginException;
import com.chen.ddd.interfaces.http.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Set;

/**
 * 异常处理
 *
 * @author chen
 * @since 2018/11/3 0:17.
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 不存在异常
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(NotExistException.class)
    @ResponseBody
    public R exception(HttpServletRequest request, HttpServletResponse response,
                       NotExistException exception) {
        log.warn("不存在异常", exception);
        return R.fail(exception.getLocalizedMessage());
    }


    /**
     * 未登录异常
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public R notLoginException(HttpServletRequest request, HttpServletResponse response,
                               NotLoginException exception) {
        log.warn("未登录异常", exception);
        return R.fail(R.NOT_LOGIN_FAIL_CODE, exception.getLocalizedMessage(), null);
    }

    /**
     * 领域运行时异常
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(DomainRuntimeException.class)
    @ResponseBody
    public R doMainRuntimeException(HttpServletRequest request, HttpServletResponse response,
                                    DomainRuntimeException exception) {
        log.warn("领域运行时异常", exception);
        return R.fail(exception.getLocalizedMessage());
    }

    /**
     * 非法参数异常
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public R exception(HttpServletRequest request, HttpServletResponse response,
                       IllegalArgumentException exception) {
        log.warn("非法参数异常", exception);
        return R.fail(exception.getLocalizedMessage());
    }

    /**
     * 非法状态异常
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public R exception(HttpServletRequest request, HttpServletResponse response,
                       IllegalStateException exception) {
        log.warn("非法状态异常", exception);
        return R.fail(exception.getLocalizedMessage());
    }

    /**
     * 非法格式异常
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(IllegalFormatException.class)
    @ResponseBody
    public R exception(HttpServletRequest request, HttpServletResponse response,
                       IllegalFormatException exception) {
        log.warn("非法格式异常", exception);
        return R.fail(exception.getLocalizedMessage());
    }

    /**
     * 参数验证失败
     * {@link javax.validation.Valid} {@link org.springframework.validation.annotation.Validated}
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R methodArgumentNotValidException(HttpServletRequest request, HttpServletResponse response,
                                             MethodArgumentNotValidException exception) {
        log.warn("方法参数验证错误异常", exception);
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return R.fail(fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * 参数验证失败
     * {@link javax.validation.Valid} {@link org.springframework.validation.annotation.Validated}
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public R bindException(HttpServletRequest request, HttpServletResponse response,
                           BindException exception) {
        log.warn("方法参数验证错误异常", exception);
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return R.fail(fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * 参数验证失败
     * {@link org.springframework.validation.annotation.Validated}
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public R constraintViolationException(HttpServletRequest request, HttpServletResponse response,
                                          ConstraintViolationException exception) {
        log.warn("方法参数验证错误异常", exception);
        Set<ConstraintViolation<?>> constraintViolationSet = exception.getConstraintViolations();
        String msg = null;
        if (CollectionUtils.isNotEmpty(constraintViolationSet)) {
            msg = constraintViolationSet.stream()
                    .findFirst()
                    .map(ConstraintViolation::getMessage)
                    .orElse(null);
        }
        return R.fail(msg);
    }

    /**
     * 缺少请求参数
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public R exception(HttpServletRequest request, HttpServletResponse response,
                       MissingServletRequestParameterException exception) {
        log.warn("缺少请求参数", exception);
        return R.fail(exception.getLocalizedMessage());
    }

    /**
     * 消息转换异常(如:json格式错误)
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public R exception(HttpServletRequest request, HttpServletResponse response,
                       HttpMessageConversionException exception) {
        log.warn("消息转换异常", exception);
        return R.fail(exception.getLocalizedMessage());
    }

    /**
     * 404错误.
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public R noHandlerFoundException(HttpServletRequest request, HttpServletResponse response,
                                     NoHandlerFoundException exception) {
        log.warn("资源不存在异常", exception);
        return R.fail(exception.getLocalizedMessage());
    }



    /**
     * 未拦截的异常
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     * @return 错误响应
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exception(HttpServletRequest request, HttpServletResponse response,
                       Exception exception) {
        log.error("出现未拦截的异常", exception);
        return R.error(exception.getLocalizedMessage());
    }
}

