package com.space.logindemo.logindemo.handler;

import cn.hutool.core.util.ObjectUtil;
import com.space.logindemo.logindemo.exceptions.ServiceException;
import com.space.logindemo.logindemo.exceptions.UserPasswordNotMatchException;
import com.space.logindemo.logindemo.model.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 12:10
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 自定义业务类异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public R handleServiceException(ServiceException e) {
        Integer code = e.getCode();
        return ObjectUtil.isNotNull(code) ? R.fail(code, e.getMsg()) : R.fail(e.getMsg());
    }

    /**
     * 用户名密码不匹配异常
     */
    @ExceptionHandler(UserPasswordNotMatchException.class)
    public R handleUserPasswordNotMatchException(UserPasswordNotMatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常，用户名或密码不匹配.", requestURI, e);
        return R.fail(e.getMessage());
    }
}
