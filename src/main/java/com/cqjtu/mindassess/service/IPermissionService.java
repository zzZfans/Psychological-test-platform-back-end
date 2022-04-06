package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
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

    Set<Permission> queryByIds(Collection<Long> ids);

}
