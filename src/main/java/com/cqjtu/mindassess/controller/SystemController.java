package com.cqjtu.mindassess.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.OperationLog;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.pojo.vo.operation.OperationPageVo;
import com.cqjtu.mindassess.pojo.vo.operation.OperationVo;
import com.cqjtu.mindassess.pojo.vo.systeminfo.SystemInfoVo;
import com.cqjtu.mindassess.service.IOperationLogService;
import com.cqjtu.mindassess.service.ISysUserService;
import com.cqjtu.mindassess.service.ISystemInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    ISysUserService userService;



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
        List<OperationLog> records = operationLogIPage.getRecords();
        List<Long> userIds = records.stream().map(OperationLog::getUserId).collect(Collectors.toList());

        OperationPageVo result = new OperationPageVo();
        result.setTotal(operationLogIPage.getTotal());
        List<User> users = userService.list(
                new LambdaQueryWrapper<User>()
                        .select(User::getId,User::getUsername)
                        .in(User::getId, userIds));

        List<OperationVo> opVos = new ArrayList<>();

        for (OperationLog record : records) {
            OperationVo opVo = new OperationVo();
            BeanUtils.copyProperties(record,opVo);
            Long uId = record.getUserId();
            for (User user : users) {
                if(uId.equals(user.getId())){
                    opVo.setUsername(user.getUsername());
                    break;
                }
            }
            opVos.add(opVo);
        }
        result.setRecords(opVos);
        return ApiResponse.success(opVos);

    }
}
