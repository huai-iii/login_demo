package com.space.logindemo.logindemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.space.logindemo.logindemo.model.entity.SysRole;

import java.util.List;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:37
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectRolePermissionByUserId(Long userId);
}
