package com.hyorin.myshop.plus.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScan(basePackages = "com.hyorin.myshop.provider.api.mapper")
public class UmsAdminProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsAdminProviderApplication.class, args);

//        Map<String,String> map = new HashMap();
//        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
//        ThreadLocal threadLocal = new ThreadLocal();
    }
}
