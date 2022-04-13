package com.cqjtu.mindassess.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.OperationLog;
import com.cqjtu.mindassess.pojo.vo.systeminfo.SystemInfoVo;
import com.cqjtu.mindassess.service.IOperationLogService;
import com.cqjtu.mindassess.service.ISystemInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Zfans
 */
@CrossOrigin
@Api(tags = {"系统信息控制器"})
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    ISystemInfoService iSystemInfoService;

    @Autowired
    IOperationLogService iOperationLogService;

    @GetMapping("/info")
    @LogOperation("获取系统资源信息")
    @ApiOperation("获取系统资源信息")
    public ApiResponse<?> systemInfo() {
        SystemInfoVo systemInfoVo = iSystemInfoService.query();
        return ApiResponse.success(systemInfoVo);
    }

    @PostMapping("/operationLog/list")
    @LogOperation("查看系统操作日志")
    @ApiOperation("查看系统操作日志")
    public ApiResponse<?> getOperationLogList(@RequestParam Map<String, Object> params,
                                              @RequestBody OperationLog operationLog) {
        IPage<OperationLog> operationLogIPage = iOperationLogService.selectPage(params, operationLog);
        return ApiResponse.success(operationLogIPage);

    }
}
