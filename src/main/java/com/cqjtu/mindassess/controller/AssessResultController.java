package com.cqjtu.mindassess.controller;



import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.pojo.req.assess.*;
import com.cqjtu.mindassess.pojo.resp.assess.AssessResultResp;
import com.cqjtu.mindassess.pojo.resp.assess.UserAnalysisResp;
import com.cqjtu.mindassess.pojo.resp.assess.UserAssessResp;
import com.cqjtu.mindassess.service.IAssessResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  测试结果控制器
 * </p>
 *
 * @author zhangzhencheng
 * @since 2022-04-06
 */
@Api(tags = {"测试结果控制器"})
@RestController
@RequestMapping("/assessResult")
public class AssessResultController {

    @Autowired
    IAssessResultService assessResultService;

    @ApiOperation(value = "添加记录")
    @LogOperation(value = "添加记录")
    @PostMapping("/save")
    public ApiResponse<?> save(@RequestBody @Validated AssessResultReq assessResultReq) {
        boolean re = assessResultService.saveAssessResult(assessResultReq);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "删除记录")
    @LogOperation(value = "删除记录")
    @GetMapping("/remove/{id}")
    public ApiResponse<?> remove(@PathVariable Long id) {
        boolean re = assessResultService.removeById(id);
        return ApiResponse.success(re);
    }


    @ApiOperation(value = "查询记录")
    @LogOperation(value = "查询记录")
    @PostMapping("/page")
    public ApiResponse<?> page(@RequestBody AssessResultPageReq pageReq) {

        Page<AssessResultResp> infoPage = assessResultService.pageList(pageReq);
        return ApiResponse.success(infoPage);
    }

    @ApiOperation(value = "查询用户个人记录")
    @LogOperation(value = "查询用户个人记录")
    @PostMapping("/userPage")
    public ApiResponse<?> userPage(@RequestBody AssessResultPageReq pageReq) {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        pageReq.setUserId(userId);
        Page<AssessResultResp> infoPage = assessResultService.pageList(pageReq);
        return ApiResponse.success(infoPage);
    }

    @ApiOperation(value = "查询用户每月测试以及异常统计查询")
    @LogOperation(value = "查询用户每月测试以及异常统计查询")
    @PostMapping("/userRecordList")
    public ApiResponse<?> userRecordList(@RequestBody RecordCountReq req) {
        List<List<Integer>> lists = assessResultService.recordCount(req);
        return ApiResponse.success(lists);
    }

    @ApiOperation(value = "获取年份")
    @LogOperation(value = "获取年份")
    @GetMapping("/getYears")
    public ApiResponse<?> getYears() {
        List<Integer> re = assessResultService.getYears();
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "获取个人状况分析")
    @LogOperation(value = "获取个人状况分析")
    @PostMapping("/getAnalysis")
    public ApiResponse<?> getAnalysis(@RequestBody AnalysisReq req) {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        req.setUserId(userId);
        UserAnalysisResp re = assessResultService.getAnalysis(req);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "获取用户测试记录列表")
    @LogOperation(value = "获取用户测试记录列表")
    @PostMapping("/getUserAssessRecord")
    public ApiResponse<?> getUserAssessRecord(@RequestBody UserAssessRecordPageReq req) {
        Page<UserAssessResp> userAssessRecord = assessResultService.getUserAssessRecord(req);
        return ApiResponse.success(userAssessRecord);
    }

    @ApiOperation(value = "获取用户历史测试记录列表")
    @LogOperation(value = "获取用户历史测试记录列表")
    @PostMapping("/getUserHistoryList")
    public ApiResponse<?> getUserHistoryList(@RequestBody AssessResultPageReq req) {
        Page<AssessResultResp> userAssessRecord = assessResultService.getUserHistory(req);
        return ApiResponse.success(userAssessRecord);
    }

    @ApiOperation(value = "获取用户历史测试记录列表")
    @LogOperation(value = "获取用户历史测试记录列表")
    @PostMapping("/getUserAnalysis")
    public ApiResponse<?> getUserAnalysis(@RequestBody AnalysisReq req) {
        Map<String, Integer> userAssessRecord = assessResultService.getUserAnalysis(req);
        return ApiResponse.success(userAssessRecord);
    }

}

