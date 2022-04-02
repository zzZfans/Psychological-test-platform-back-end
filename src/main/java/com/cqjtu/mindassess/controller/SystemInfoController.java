package com.cqjtu.mindassess.controller;


import com.cqjtu.mindassess.annotation.OperationLog;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.pojo.vo.systeminfo.SystemInfoVo;
import com.cqjtu.mindassess.service.ISystemInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zfans
 */
@CrossOrigin
@Api(tags = {"系统信息控制器"})
@RestController
@RequestMapping("/system")
public class SystemInfoController {

    @Autowired
    ISystemInfoService iSystemInfoService;

    @RequiresPermissions("systemInfo:view")
    @GetMapping("/info")
    @OperationLog("获取系统资源")
    @ApiOperation("获取系统信息")
    public ApiResponse<?> systemInfo() {
        SystemInfoVo systemInfoVo = iSystemInfoService.query();
        return ApiResponse.success(systemInfoVo);
    }
}
