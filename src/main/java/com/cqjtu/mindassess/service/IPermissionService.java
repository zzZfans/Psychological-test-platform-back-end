package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqjtu.mindassess.pojo.vo.PermissionVo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 根据permission主键集合，查询permission实体集合
     * @param ids 主键集合
     * @return 实体集合
     */
    Set<Permission> queryByIds(Collection<Long> ids);


    /**
     * 按照父子权限关系查询系统中权限信息
     * @return 权限集合
     */
    List<PermissionVo> queryPermissionWithRelation();

}
