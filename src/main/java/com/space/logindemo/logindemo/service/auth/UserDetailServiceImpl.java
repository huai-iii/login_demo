package com.space.logindemo.logindemo.service.auth;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.logindemo.logindemo.enums.UserStatusEnums;
import com.space.logindemo.logindemo.exceptions.ServiceException;
import com.space.logindemo.logindemo.mapper.SysUserMapper;
import com.space.logindemo.logindemo.model.login.LoginUser;
import com.space.logindemo.logindemo.model.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:15
 */
@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUsername, username);
        SysUser user = sysUserMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：【" + username + " 】不存在");
        } else if (UserStatusEnums.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("对不起，您的账号：【" + username + " 】已停用");
        } else if (UserStatusEnums.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：【" + username + " 】已被删除");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setUser(user);
        // 查询用户权限
        loginUser.setPermissions(sysPermissionService.getMenuPermission(user));
        return loginUser;
    }
}
