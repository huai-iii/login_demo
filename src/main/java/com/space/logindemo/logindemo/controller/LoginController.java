package com.space.logindemo.logindemo.controller;

import com.space.logindemo.logindemo.model.login.LoginBody;
import com.space.logindemo.logindemo.model.R;
import com.space.logindemo.logindemo.service.auth.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 11:58
 */
@RestController
public class LoginController {
    @Resource
    LoginService loginService;
    @PostMapping("/login")
    public R login(@RequestBody LoginBody loginBody) {
        String token = loginService.login(
                loginBody.getUsername(),
                loginBody.getPassword()
        );
        return R.ok().put("token", token);
    }
}
