package com.space.logindemo.logindemo.model;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 12:00
 */
@Data
public class R extends HashMap<String, Object> {
    public R() {}

    public R(Integer code, String msg, Object data) {
        super.put("code", code);
        super.put("msg", msg);
        super.put("data", data);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static R ok() {
        return new R(0, "操作成功", null);
    }

    public static R ok(Object data) {
        return new R(0, "操作成功", data);
    }

    public static R ok(String msg) {
        return new R(0, msg, null);
    }

    public static R fail() {
        return new R(1, "操作失败", null);
    }

    public static R fail(Integer code, String msg) {
        return new R(code, msg, null);
    }

    public static R fail(String msg) {
        return new R(1, msg, null);
    }

}
