package com.space.logindemo.logindemo.service.impl;

import com.space.logindemo.logindemo.mapper.SysMenuMapper;
import com.space.logindemo.logindemo.model.entity.SysMenu;
import com.space.logindemo.logindemo.service.ISysMenuService;
import com.space.logindemo.logindemo.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:55
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
    @Resource
    SysMenuMapper menuMapper;

    @Override
    public List<String> selectMenusPermsByUserId(Long userId) {
        return menuMapper.selectMenusPermsByUserId(userId);
    }

}
