package com.cqjtu.mindassess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cqjtu.mindassess.entity.Role;
import com.cqjtu.mindassess.entity.RolePermission;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.mapper.RoleMapper;
import com.cqjtu.mindassess.pojo.vo.RoleInfoVo;
import com.cqjtu.mindassess.service.IRolePermissionService;
import com.cqjtu.mindassess.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Resource
    IRolePermissionService rolePermissionService;
    @Resource
    ISysUserService userService;

    @Override
    public Role queryRoleByName(String roleName) {
        return getOne(new LambdaQueryWrapper<Role>()
                .eq(Role::getRoleName, roleName));
    }

    @Override
    public List<Role> queryRolesByIds(Set<Long> ids) {
        return roleMapper.selectBatchIds(ids);
    }

    @Override
    public void deleteWithPermission(Long roleId) {
        if (!removeById(roleId)) {
            throw new BusinessException("删除角色失败");
        }
        rolePermissionService.remove(
                new LambdaUpdateWrapper<RolePermission>()
                        .eq(RolePermission::getRoleId, roleId));
    }

    @Override
    public List<RoleInfoVo> listRoleInfo() {
        return roleMapper.listRoleInfo();
    }
}
