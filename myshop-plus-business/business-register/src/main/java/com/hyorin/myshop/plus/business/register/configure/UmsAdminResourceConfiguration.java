package com.hyorin.myshop.plus.business.register.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 注入BCryptPasswordEncoder
 * 在用户注册时对密码进行加密
 */
@Configuration
public class UmsAdminResourceConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
