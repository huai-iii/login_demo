package com.space.logindemo.logindemo.service.auth;
import com.space.logindemo.logindemo.exceptions.ServiceException;
import com.space.logindemo.logindemo.exceptions.UserPasswordNotMatchException;
import com.space.logindemo.logindemo.model.login.LoginUser;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 11:59
 */
@Service
public class LoginService {

    @Resource
    TokenService tokenService;
    @Resource
    AuthenticationManager authenticationManager;
    public String login(String username, String password) {
        Authentication authentication = null;
        try {
            // 设置当前用户的身份验证信息
            UsernamePasswordAuthenticationToken tokens = new UsernamePasswordAuthenticationToken(username, password);
            // 自动调用 UserDetailService 的 loadUserByUsername 方法
            authentication = authenticationManager.authenticate(tokens);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException("用户密码错误");
            } else {
                throw new ServiceException(e.getMessage());
            }
        }
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(principal);
    }
}
