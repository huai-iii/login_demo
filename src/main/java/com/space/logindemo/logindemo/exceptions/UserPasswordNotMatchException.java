package com.space.logindemo.logindemo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 12:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserPasswordNotMatchException extends RuntimeException {
    private String message;
}
