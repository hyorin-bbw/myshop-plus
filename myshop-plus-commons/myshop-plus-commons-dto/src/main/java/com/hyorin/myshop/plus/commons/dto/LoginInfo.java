package com.hyorin.myshop.plus.commons.dto;

import lombok.Data;

/**
 * 用户信息
 * <p>
 * Description:
 * </p>
 *
 * @author hyorin
 * @version v1.0.0
 * @date 2020.10.16
 * @see com.hyorin.myshop.plus.commons.dto.LoginInfo
 */
@Data
public class LoginInfo {

    private String token;

    private String name;

    private String avatar;

}
