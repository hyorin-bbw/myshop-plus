package com.hyorin.myshop.provider.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyorin.myshop.plus.commons.domain.UmsAdmin;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hyorin
 * @since 2020-10-13
 */
public interface IUmsAdminService extends IService<UmsAdmin> {
    /**
     * 新增
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return {@code boolean}
     */
    boolean create(UmsAdmin umsAdmin);

    /**
     * 删除
     *
     * @param id {@code Long}
     * @return {@code boolean}
     */
    boolean remove(Long id);

    /**
     * 编辑
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return {@code boolean}
     */
    boolean update(UmsAdmin umsAdmin);

    /**
     * 根据id获取
     *
     * @param id {@code Long}
     * @return {@link UmsAdmin}
     */
    UmsAdmin get(Long id);

    /**
     * 根据用户名获取
     *
     * @param username
     * @return
     */
    UmsAdmin getByUsername(String username);

    /**
     * 分页
     *
     * @param current  {@code int} 页码
     * @param size     {@code int} 笔数
     * @param umsAdmin {@link UmsAdmin}
     * @return {@code IPage<UmsAdmin>}
     */
    IPage<UmsAdmin> page(int current, int size, UmsAdmin umsAdmin);
}
