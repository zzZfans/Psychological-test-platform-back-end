package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqjtu.mindassess.pojo.vo.RoleInfoVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
public interface IRoleService extends IService<Role> {


    /**
     * 根据角色名，查询角色信息
     * @param roleName 角色名
     * @return 存在角色返回Role实例，不存在返回null
     */
    Role queryRoleByName(String roleName);


    List<Role> queryRolesByIds(Set<Long> ids);

    /**
     * 根据roleId，删除role 及其 rolePermission关系
     * @param roleId
     */
    void deleteWithPermission(Long roleId);


    /**
     * 查询角色表的所有Role信息
     * @return
     */
    List<RoleInfoVo> listRoleInfo();

}
