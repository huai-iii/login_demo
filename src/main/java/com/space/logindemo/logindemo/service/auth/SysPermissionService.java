package com.space.logindemo.logindemo.service.auth;

import com.space.logindemo.logindemo.model.entity.SysUser;
import com.space.logindemo.logindemo.service.ISysMenuService;
import com.space.logindemo.logindemo.service.ISysRoleService;
import com.space.logindemo.logindemo.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:24
 */
@Component
public class SysPermissionService {
    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISysRoleService roleService;

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {

        Set<String> perms = new HashSet<>();
        if (SecurityUtils.isAdmin(user.getUserId())) {
            // 如果是管理员，权限赋予 *:*:* 表示拥有所有权限
            perms.add("*:*:*");
        } else {
            // 否则查询实际权限
            perms.addAll(sysMenuService.selectMenusPermsByUserId(user.getUserId()));
        }
        return perms;
    }

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (SecurityUtils.isAdmin(user.getUserId())) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }
}