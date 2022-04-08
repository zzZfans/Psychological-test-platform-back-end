package com.cqjtu.mindassess.service.impl;

import com.cqjtu.mindassess.entity.RolePermission;
import com.cqjtu.mindassess.mapper.RolePermissionMapper;
import com.cqjtu.mindassess.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author auther
 * @since 2022-04-08
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    @Override
    public List<RolePermission> queryByIds(Collection<Long> ids) {
        return listByIds(ids);
    }
}
