package com.cqjtu.mindassess.mapper;

import com.cqjtu.mindassess.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqjtu.mindassess.pojo.vo.RoleInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    List<RoleInfoVo> listRoleInfo();

}
