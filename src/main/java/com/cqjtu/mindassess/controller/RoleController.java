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
        // 判断是否存在该角色名
        if(!ObjectUtils.isEmpty(roleService.queryRoleByName(dto.getRoleName()))){
            throw new BusinessException("当前角色已经存在");
        }
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        roleService.saveOrUpdate(role);
        Long roleId = role.getId();
        // 该role 所指定的新的permissionId
        List<Long> newPermissionIds = dto.getPermissionIdList();
        // 验证新指定的是否合法
        if (newPermissionIds.size() != 0) {
            int dbCount = permissionService.count(new LambdaQueryWrapper<Permission>().select(Permission::getId).in(Permission::getId, newPermissionIds));
            if (dbCount != newPermissionIds.size()) {
                throw new BusinessException("存在不支持的权限");
            }
        }
        // 查询当前角色的关系表
        List<RolePermission> dbRolePermission = rolePermissionService.list(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, roleId));
        List<Long> dbPermissionIds = dbRolePermission.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        List<Long> newPermissionIdsCopy = new ArrayList<>(newPermissionIds);
        // 查询没有的权限做增加
        newPermissionIdsCopy.removeAll(dbPermissionIds);
        if (newPermissionIdsCopy.size() != 0) {
            List<RolePermission> rpList = new ArrayList<>();
            for (Long pId : newPermissionIdsCopy) {
                RolePermission rp = new RolePermission();
                rp.setRoleId(roleId);
                rp.setPermissionId(pId);
                rpList.add(rp);
            }
            rolePermissionService.saveBatch(rpList, rpList.size());
        }
        // 原数据库存在而当前不存在的做删除
        dbPermissionIds = dbRolePermission.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        dbPermissionIds.removeAll(newPermissionIds);
        if (dbPermissionIds.size() != 0) {
            //删除
            List<Long> finalDbPermissionIds = dbPermissionIds;
            List<Long> deleteIds = dbRolePermission.stream().filter(rolePermission -> {
                Long pid = rolePermission.getPermissionId();
                for (Long dbPermissionId : finalDbPermissionIds) {
                    if (dbPermissionId.equals(pid)) {
                        return true;
                    }
                }
                return false;
            }).map(RolePermission::getId).collect(Collectors.toList());
            rolePermissionService.removeByIds(deleteIds);
        }
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

        if( ObjectUtils.isEmpty(permissionIds)){
            List<Permission> list = new ArrayList<>();
            return ApiResponse.success(list);
        }

        List<Permission> list = permissionService.list(
                new LambdaQueryWrapper<Permission>()
                        .in(Permission::getId,permissionIds)
                        .orderByAsc(Permission::getSort));

        List<Long> sortedPermissionIds = list.stream().map(Permission::getId).collect(Collectors.toList());
        return ApiResponse.success(sortedPermissionIds);
    }

    @ApiOperation("根据角色id删除角色")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<?> deleteByRoleId(@PathVariable(value = "id") Long roleId) {
        if (ObjectUtils.isEmpty(roleService.getById(roleId))) {
            throw new BusinessException("不存在当前角色");
        }
        roleService.deleteWithPermission(roleId);
        return ApiResponse.success();
    }

}
