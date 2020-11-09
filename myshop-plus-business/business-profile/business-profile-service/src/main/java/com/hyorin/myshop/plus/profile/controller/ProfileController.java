package com.hyorin.myshop.plus.profile.controller;

import com.hyorin.myshop.plus.commons.domain.UmsAdmin;
import com.hyorin.myshop.plus.commons.dto.ResponseResult;
import com.hyorin.myshop.provider.api.service.IUmsAdminService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 个人信息管理
 * </p>
 *
 * @author hyorin
 * @since 2020-10-19
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {

    @Reference(version = "1.0.0")
    private IUmsAdminService umsAdminService;

    /**
     * 获取个人个人信息
     *
     * @param username 用户名
     * @return {@link ResponseResult}
     */
    @GetMapping("user/{username}")
    public ResponseResult<UmsAdmin> info(@PathVariable String username) {
        UmsAdmin umsAdmin = umsAdminService.getByUsername(username);
        return new ResponseResult<>(ResponseResult.Code.SUCCESS.getValue(), "获取用户信息成功", umsAdmin);
    }
}
