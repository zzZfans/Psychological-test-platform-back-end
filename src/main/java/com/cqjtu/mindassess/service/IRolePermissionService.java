package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author auther
 * @since 2022-04-08
 */
public interface IRolePermissionService extends IService<RolePermission> {


    /**
     * 根据角色id集合，查询所有的角色权限关系
     * @param ids
     * @return
     */
    List<RolePermission> queryByIds(Collection<Long> ids);

}
