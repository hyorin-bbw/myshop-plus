package com.hyorin.myshop.plus.profile.feign;

import com.hyorin.myshop.plus.configure.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 个人信息管理
 * <p>
 * @Description
 * </p>
 *
 * @author hyorin
 * @version 1.0.0
 * @date 2020.10.17
 */
@FeignClient(value = "business-profile",path = "profile",configuration = FeignRequestConfiguration.class)
public interface ProfileFeign {

    @GetMapping(value = "info/{username}")
    String info(@PathVariable String  username);
}
