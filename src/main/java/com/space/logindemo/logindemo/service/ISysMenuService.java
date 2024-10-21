package com.space.logindemo.logindemo.service;

import java.util.List;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:32
 */
public interface ISysMenuService {
    /**
     * 根据 userId 查询权限
     *
     * @param userId
     * @return
     */
    List<String> selectMenusPermsByUserId(Long userId);
}
