package com.cqjtu.mindassess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cqjtu.mindassess.entity.Role;
import com.cqjtu.mindassess.mapper.RoleMapper;
import com.cqjtu.mindassess.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public Role queryRoleByName(String roleName) {
        return getOne(new LambdaQueryWrapper<Role>()
                .eq(Role::getRoleName,roleName));
    }

    @Override
    public List<Role> queryRolesByIds(Set<Long> ids) {
        return roleMapper.selectBatchIds(ids);
    }

    @Override
    public Set<Role> queryROlesByIds(Set<Long> ids) {
        List<Role> roles = queryRolesByIds(ids);
        return new HashSet<>(roles);
    }
}
