package com.hyorin.myshop.plus.commons.dto;


import lombok.Data;

/**
 * 用户登录参数
 * <p>
 * Description: 用户登录参数实体类
 * </p>
 *
 * @author hyorin
 * @version v1.0.0
 * @date 2020.10.16
 * @see com.hyorin.myshop.plus.commons.dto.LoginParams
 */

@Data
public class LoginParams {

    /**
     * 登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


}
