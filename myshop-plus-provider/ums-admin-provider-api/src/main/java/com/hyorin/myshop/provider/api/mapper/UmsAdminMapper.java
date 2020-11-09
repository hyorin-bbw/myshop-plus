package com.hyorin.myshop.provider.api.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyorin.myshop.plus.commons.domain.UmsAdmin;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hyorin
 * @since 2020-10-13
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {


    /**
     * 根据用户名获取
     *
     * @param username
     * @return
     */
    UmsAdmin getByUsername(String username);
}
