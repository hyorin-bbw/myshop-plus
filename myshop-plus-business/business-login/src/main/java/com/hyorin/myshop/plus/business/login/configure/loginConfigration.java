package com.hyorin.myshop.plus.business.login.configure;


import com.hyorin.myshop.business.oauth2.service.UserDetailServiceImpl;
import com.hyorin.myshop.plus.profile.feign.ProfileFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * <p>
 * 用户登录配置
 * </p>
 *
 * @author hyorin
 * @since 2020-10-16
 */
@Configuration
public class loginConfigration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

}
