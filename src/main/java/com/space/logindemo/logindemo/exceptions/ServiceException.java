package com.space.logindemo.logindemo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 12:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private Integer code;
    private String msg;
    public ServiceException(String msg) {
        this.msg = msg;
    }
}
