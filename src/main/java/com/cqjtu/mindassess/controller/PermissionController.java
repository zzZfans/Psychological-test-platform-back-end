package com.cqjtu.mindassess.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.Permission;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.pojo.req.permission.PermissionDto;
import com.cqjtu.mindassess.service.IPermissionService;
import com.cqjtu.mindassess.util.EmptyChecker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
@Api(tags = {"权限控制器"})
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    IPermissionService permissionService;


    /**
     * 菜单类型权限
     */
    public static final int PERMISSION_TYPE_MENU = 0;

    /**
     * 按钮类型权限
     */
    public static final int PERMISSION_TYPE_BUTTON =1;



    @ApiOperation("添加权限")
    @PostMapping("/add")
    public ApiResponse<?> addMenuPermission(@Validated @RequestBody PermissionDto permissionDto) {
        Integer type = permissionDto.getPermissionType();
        //check
        if( type == PERMISSION_TYPE_MENU ){
            if( EmptyChecker.isEmpty(permissionDto.getPermissionName()) ||
                    EmptyChecker.isEmpty(permissionDto.getParentId()) ||
            EmptyChecker.isEmpty(permissionDto.getRedirect()) ||
            EmptyChecker.isEmpty(permissionDto.getComponent()) ){
                throw new BusinessException("权限名,父级权限,路由名,前端组件 为必填");
            }
        }else if ( type == PERMISSION_TYPE_BUTTON ){
            if( EmptyChecker.isEmpty(permissionDto.getPermissionName()) ||
            EmptyChecker.isEmpty(permissionDto.getParentId()) ||
            EmptyChecker.isEmpty(permissionDto.getPermission()) ) {
                throw new BusinessException("权限名,父级权限,权限标识 为必填");
            }
        }else {
            throw new BusinessException("没有该方式的权限类型");
        }
        Long parentId = permissionDto.getParentId();
        Permission parent = null;
        if( parentId != 0 ){
            parent = permissionService.getById(parentId);
            if ( EmptyChecker.isEmpty(parent) ) {
                throw new BusinessException("父组件不存在");
            }
        }
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionDto,permission);
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        permission.setCreatorId(userId);
        permission.setUpdaterId(userId);
        boolean success = permissionService.save(permission);
        if( success ){
            return ApiResponse.success(200,"添加成功");
        }
        return ApiResponse.fail(200,"添加失败");
    }

}
