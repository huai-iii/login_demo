package com.space.logindemo.logindemo;

import cn.hutool.crypto.digest.BCrypt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginDemoApplicationTests {

    @Test
    void contextLoads() {
        String s = BCrypt.hashpw("123456");
        System.out.println(s);
    }

}
