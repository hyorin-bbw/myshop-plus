package com.hyorin.myshop.plus.configure;

import com.hyorin.myshop.plus.interceptor.FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign全局配置
 * <p>
 * @Description
 * </p>
 *
 * @author hyorin
 * @version 1.0.0
 * @date 2020.10.17
 * @see FeignRequestConfiguration
 */
@Configuration
public class FeignRequestConfiguration {

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
