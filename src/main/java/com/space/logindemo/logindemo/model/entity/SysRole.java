package com.space.logindemo.logindemo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:33
 */
@Data
@TableName("sys_role")
public class SysRole  implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;
    private String roleName;
    private String roleKey;
}
