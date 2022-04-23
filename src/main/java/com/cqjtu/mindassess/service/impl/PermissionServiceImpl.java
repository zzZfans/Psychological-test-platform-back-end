package com.cqjtu.mindassess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cqjtu.mindassess.entity.Permission;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.mapper.PermissionMapper;
import com.cqjtu.mindassess.pojo.vo.PermissionInfoVo;
import com.cqjtu.mindassess.pojo.vo.PermissionVo;
import com.cqjtu.mindassess.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    ISysUserService userService;
    /**
     * 默认顶级权限的父权限id
     */
    private static final Long DEFAULT_PARENT_ID = 0L;

    @Override
    public Set<Permission> queryByIds(Collection<Long> ids) {
        return new HashSet<>(listByIds(ids));
    }

    @Override
    public List<PermissionVo> queryPermissionWithRelation() {
        // 查询顶级 parentId = 0
        PermissionVo root = new PermissionVo();
        root.setId(DEFAULT_PARENT_ID);
        queryChildren(root);
        return root.getChildren();
    }

    @Override
    public List<PermissionInfoVo> listPermissionInfoVos() {
        return baseMapper.listPermissionInfoVo();
    }

    /**
     * 递归查询以当前id 为父id的permission
     *
     * @param v permissionVo
     */
    private void queryChildren(PermissionVo v) {
        Long parentId = v.getId();
        List<Permission> list = list(new LambdaQueryWrapper<Permission>()
                .eq(Permission::getParentId, parentId)
                .orderByAsc(Permission::getSort));

        if (ObjectUtils.isEmpty(list)) {
            return;
        }

        List<PermissionVo> voList = new ArrayList<>(list.size());
        for (Permission permission : list) {
            PermissionVo vo = new PermissionVo();
            BeanUtils.copyProperties(permission, vo);
            Long creatorId = permission.getCreatorId();
            Long updaterId = permission.getUpdaterId();
            User createUser = userService.getById(creatorId);
            User updateUser = userService.getById(updaterId);
            String creator = createUser == null ? null : createUser.getUsername();
            String updater = updateUser == null ? null : updateUser.getUsername();
            vo.setCreateBy(creator);
            vo.setUpdateBy(updater);
            voList.add(vo);
        }
        v.setChildren(voList);
        for (PermissionVo permissionVo : voList) {
            queryChildren(permissionVo);
        }
    }
}
