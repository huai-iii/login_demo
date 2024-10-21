package com.space.logindemo.logindemo.config.security.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.space.logindemo.logindemo.model.login.LoginUser;
import com.space.logindemo.logindemo.model.R;
import com.space.logindemo.logindemo.service.auth.TokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * @author leoyin
 * @Date 2024/10/19 13:19
 * @description
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Resource
    TokenService tokenService;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtil.isNotNull(loginUser)) {
            tokenService.deleteLoginUser(loginUser.getToken());
        }
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSONUtil.toJsonStr(R.ok("退出成功")));
    }
}
