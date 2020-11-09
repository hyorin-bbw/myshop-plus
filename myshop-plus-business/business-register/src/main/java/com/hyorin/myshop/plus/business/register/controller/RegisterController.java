package com.hyorin.myshop.plus.business.register.controller;

import com.hyorin.myshop.plus.commons.domain.UmsAdmin;
import com.hyorin.myshop.plus.commons.dto.ResponseResult;
import com.hyorin.myshop.provider.api.service.IUmsAdminService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 用户注册 前端控制器
 * </p>
 *
 * @author hyorin
 * @since 2020-10-13
 */
@RestController
@RequestMapping(value = "register")
public class RegisterController {

    @Reference(version = "1.0.0")
    private IUmsAdminService umsAdminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 用户注册
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return 成功则返回新注册用户的信息
     */
    public ResponseResult<UmsAdmin> reg(@RequestBody UmsAdmin umsAdmin) {
        String message = ValidateRegister(umsAdmin);
        //用户不存在,验证通过
        if (message == null) {
            //用户密码加密
            umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
            boolean result = umsAdminService.create(umsAdmin);
            if (result) {
                UmsAdmin admin = umsAdminService.get(umsAdmin.getId());
                return new ResponseResult<UmsAdmin>(HttpStatus.OK.value(), "新用户注册成功", admin);
            }
        }
        return new ResponseResult<UmsAdmin>(HttpStatus.NOT_ACCEPTABLE.value(), "新用户注册失败");
    }

    /**
     * 验证注册信息
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return
     */
    private String ValidateRegister(UmsAdmin umsAdmin) {
        UmsAdmin admin = umsAdminService.get(umsAdmin.getId());
        if (admin != null) {
            return "用户名已存在，请重新输入";
        }
        return null;
    }

}
