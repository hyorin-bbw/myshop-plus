package com.hyorin.myshop.plus.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyorin.myshop.plus.commons.domain.UmsAdmin;
import com.hyorin.myshop.provider.api.mapper.UmsAdminMapper;
import com.hyorin.myshop.provider.api.service.IUmsAdminService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hyorin
 * @since 2020-10-13
 */
@Service(version = "1.0.0")
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {

    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Override
    public boolean create(UmsAdmin umsAdmin) {
        return super.save(umsAdmin);
    }

    @Override
    public boolean remove(Long id) {
        return super.removeById(id);
    }

    @Override
    public boolean update(UmsAdmin umsAdmin) {
        return super.updateById(umsAdmin);
    }

    @Override
    public UmsAdmin get(Long id) {
        return super.getById(id);
    }

    @Override
    public UmsAdmin getByUsername(String username) {
        return umsAdminMapper.getByUsername(username);
    }

    @Override
    public IPage<UmsAdmin> page(int current, int size, UmsAdmin umsAdmin) {
        Page<UmsAdmin> page = new Page<>(current, size);
        LambdaQueryWrapper<UmsAdmin> wrapper = new LambdaQueryWrapper<>();

        // TODO 查询
        // TODO 排序

        return super.page(page, wrapper);
    }
}
