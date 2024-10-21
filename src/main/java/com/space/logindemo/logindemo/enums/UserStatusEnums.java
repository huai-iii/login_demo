package com.space.logindemo.logindemo.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 16:16
 */
@Getter
public enum UserStatusEnums {
    OK(0L, "正常"),
    DISABLE(1L, "停用"),
    DELETED(2L, "删除");

    private final Long code;
    private final String info;

    UserStatusEnums(Long code, String info) {
        this.code = code;
        this.info = info;
    }
}
