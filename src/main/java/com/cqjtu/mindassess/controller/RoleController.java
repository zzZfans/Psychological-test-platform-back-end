package com.cqjtu.mindassess.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.Role;
import com.cqjtu.mindassess.entity.RolePermission;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.service.IPermissionService;
import com.cqjtu.mindassess.service.IRolePermissionService;
import com.cqjtu.mindassess.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
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
    @GetMapping("/list")
    public ApiResponse<?> roleList() {
        return ApiResponse.success();
    }

    @ApiOperation("添加角色或修改角色")
    @PostMapping("/saveOrUpdate")
    public ApiResponse<?> saveOrUpdate() {
        return ApiResponse.success();
    }

    @ApiOperation("查询指定角色id下所有权限")
    @GetMapping("/permissionIdList/{id}")
    public ApiResponse<?> rolePermissionListById(@PathVariable Long id) {
        if(ObjectUtils.isEmpty(roleService.getById(id))){
            throw new BusinessException("系统不存在该角色, roleId=" + id);
        }
        List<RolePermission> rolePermissions = rolePermissionService.list(
                new LambdaQueryWrapper<RolePermission>()
                        .eq(RolePermission::getRoleId, id));
        List<Long> permissionIds = rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());

        return ApiResponse.success(permissionIds);
    }

}
