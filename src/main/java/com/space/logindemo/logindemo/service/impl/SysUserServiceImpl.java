package com.space.logindemo.logindemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.logindemo.logindemo.model.entity.SysUser;
import com.space.logindemo.logindemo.service.ISysUserService;
import com.space.logindemo.logindemo.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2024-10-19 13:11:53
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements ISysUserService {

}




