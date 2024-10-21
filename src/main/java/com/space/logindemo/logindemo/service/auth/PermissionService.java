package com.space.logindemo.logindemo.service.auth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.space.logindemo.logindemo.model.login.LoginUser;
import com.space.logindemo.logindemo.utils.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:43
 */
@Component("ss")
public class PermissionService {
    /**
     * 判断是否具备【单个】权限
     * @param permission
     * @return
     */
    public boolean hasPermi(String permission) {
        if (StrUtil.isBlank(permission)) {
            return false;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (ObjectUtil.isNull(loginUser) || CollUtil.isEmpty(loginUser.getPermissions())) {
            return false;
        }
        return hasPermission(loginUser.getPermissions(), permission);
    }

    /**
     * 判断是否具备多个权限中【某个】权限
     * @param permission
     * @return
     */
    public boolean hasAnyPermi(String permission) {
        if (ArrayUtil.isEmpty(permission)) {
            return false;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (ObjectUtil.isNull(loginUser) || CollUtil.isEmpty(loginUser.getPermissions())) {
            return false;
        }
        String[] perms = permission.split(",");
        for (String perm : perms) {
            if (perm != null && hasPermission(loginUser.getPermissions(), perm)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPermission(Set<String> permissions, String permission) {
        return permissions.contains("*:*:*") || permissions.contains(StrUtil.trim(permission));
    }
}
