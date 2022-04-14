package com.cqjtu.mindassess.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.Permission;
import com.cqjtu.mindassess.entity.Role;
import com.cqjtu.mindassess.entity.RolePermission;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.pojo.req.role.RoleDto;
import com.cqjtu.mindassess.pojo.vo.PermissionInfoVo;
import com.cqjtu.mindassess.pojo.vo.RoleInfoVo;
import com.cqjtu.mindassess.pojo.vo.RolePermissionInfoVo;
import com.cqjtu.mindassess.service.IPermissionService;
import com.cqjtu.mindassess.service.IRolePermissionService;
import com.cqjtu.mindassess.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
@Api(tags = {"角色控制器"})
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    IRoleService roleService;
    @Resource
    IPermissionService permissionService;
    @Resource
    IRolePermissionService rolePermissionService;

    @ApiOperation("查询角色列表")
    @GetMapping("/list/andPermissionList")
    public ApiResponse<?> roleList() {
        RolePermissionInfoVo result = new RolePermissionInfoVo();
        List<RoleInfoVo> roleInfoVos = roleService.listRoleInfo();
        List<PermissionInfoVo> permissionInfoVos = permissionService.listPermissionInfoVos();
        result.setRoleList(roleInfoVos);
        result.setPermissionList(permissionInfoVos);
        return ApiResponse.success(result);
    }

    @ApiOperation("添加角色或修改角色")
    @PostMapping("/saveOrUpdate")
    public ApiResponse<?> saveOrUpdate(@Validated @RequestBody RoleDto dto) {
        //TODO
        boolean save = ObjectUtils.isEmpty(dto.getId());   // 如果没有携带主键，则为添加
        Role role = new Role();
        BeanUtils.copyProperties(dto,role);
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        if( save ){
            roleService.save(role);
        }else {
            role.setUpdaterId(userId);
            roleService.updateById(role);
        }
        List<Long> permissionIds = dto.getPermissionIdList();
        if(ObjectUtils.isEmpty(permissionIds)){
            return ApiResponse.success();
        }
        if (permissionService.queryByIds(permissionIds).size() != permissionIds.size()) {
            throw new BusinessException("存在系统不存在的权限");
        }

        Long roleId = role.getId();
        if(save){
            List<RolePermission> list = new ArrayList<>();
            for (Long permissionId : permissionIds) {
                RolePermission rp = new RolePermission();
                rp.setPermissionId(permissionId);
                rp.setRoleId(roleId);
                list.add(rp);
            }
            if (rolePermissionService.saveBatch(list)) {
                return ApiResponse.success();
            }
        }
        //删除原RolePermission关系
        rolePermissionService.remove(
                new LambdaUpdateWrapper<RolePermission>()
                        .eq(RolePermission::getRoleId,roleId));
        List<RolePermission> list = new ArrayList<>();
        for (Long permissionId : permissionIds) {
            RolePermission rp = new RolePermission();
            rp.setPermissionId(permissionId);
            rp.setRoleId(roleId);
            list.add(rp);
        }
        rolePermissionService.saveBatch(list);
        return ApiResponse.success();
    }


    @ApiOperation("查询指定角色id下所有权限")
    @GetMapping("/permissionIdList/{id}")
    public ApiResponse<?> rolePermissionListById(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(roleService.getById(id))) {
            throw new BusinessException("系统不存在该角色, roleId=" + id);
        }
        List<RolePermission> rolePermissions = rolePermissionService.list(
                new LambdaQueryWrapper<RolePermission>()
                        .eq(RolePermission::getRoleId, id));
        List<Long> permissionIds = rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());

        List<Permission> list = permissionService.list(
                new LambdaQueryWrapper<Permission>()
                        .orderByAsc(Permission::getSort));

        List<Long> sortedPermissionIds = list.stream().map(Permission::getId).collect(Collectors.toList());
        return ApiResponse.success(sortedPermissionIds);
    }

    @ApiOperation("根据角色id删除角色")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<?> deleteByRoleId(@PathVariable(value = "id") Long roleId) {
        if( ObjectUtils.isEmpty(roleService.getById(roleId))){
            throw new BusinessException("不存在当前角色");
        }
        roleService.deleteWithPermission(roleId);
        return ApiResponse.success();
    }

}
