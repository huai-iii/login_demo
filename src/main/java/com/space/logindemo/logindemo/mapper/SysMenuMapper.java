package com.space.logindemo.logindemo.mapper;

import java.util.List;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:38
 */
public interface SysMenuMapper {
    List<String> selectMenusPermsByUserId(Long userId);
}
