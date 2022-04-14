package com.cqjtu.mindassess.mapper;

import com.cqjtu.mindassess.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqjtu.mindassess.pojo.vo.PermissionInfoVo;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    /**
     * 查询权限信息vo
     * @return 权限信息vo集合
     */
    List<PermissionInfoVo> listPermissionInfoVo();

}
