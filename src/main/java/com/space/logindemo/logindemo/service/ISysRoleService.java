package com.space.logindemo.logindemo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.space.logindemo.logindemo.model.entity.SysRole;

import java.util.Set;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:33
 */
public interface ISysRoleService extends IService<SysRole> {

    Set<String> selectRolePermissionByUserId(Long userId);
}
