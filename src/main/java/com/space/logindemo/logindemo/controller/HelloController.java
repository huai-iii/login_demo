package com.space.logindemo.logindemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 12:36
 */
@RestController
@RequestMapping("/demo")
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("@ss.hasPermi('system:demo:list')")
    public String hello() {
        return "Hello World!";
    }
}
