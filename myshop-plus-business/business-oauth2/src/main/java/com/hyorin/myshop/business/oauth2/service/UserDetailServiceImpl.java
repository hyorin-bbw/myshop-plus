package com.hyorin.myshop.business.oauth2.service;

import com.google.common.collect.Lists;
import com.hyorin.myshop.plus.commons.domain.UmsAdmin;
import com.hyorin.myshop.provider.api.service.IUmsAdminService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义用户认证授权
 *
 * @author hyorin
 * @version 1.0.0
 * @date 2020.10.14
 * @see com.hyorin.myshop.business.oauth2.service
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    IUmsAdminService umsAdminService;

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "$2a$10$xcP2Av51xvLv0u1mH3Mu0OMeug0J5t8GTke88fjktL1pgD.EGVFOe";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户
        UmsAdmin umsAdmin = umsAdminService.getByUsername(username);
        //匹配用户名,默认所有用户拥有USER权限
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);

        //用户存在
        if (umsAdmin != null) {
            return new User(USERNAME, PASSWORD, grantedAuthorities);
        }
        //用户不存在
        else {
            return null;
        }

    }
}
