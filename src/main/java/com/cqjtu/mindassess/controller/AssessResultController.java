package com.cqjtu.mindassess.controller;



import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.pojo.req.assess.AssessResultPageReq;
import com.cqjtu.mindassess.pojo.req.assess.AssessResultReq;
import com.cqjtu.mindassess.pojo.req.assess.RecordCountReq;
import com.cqjtu.mindassess.pojo.resp.assess.AssessResultResp;
import com.cqjtu.mindassess.service.IAssessResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @PostMapping("/save")
    public ApiResponse<?> save(@RequestBody @Validated AssessResultReq assessResultReq) {
        boolean re = assessResultService.saveAssessResult(assessResultReq);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "删除记录")
    @GetMapping("/remove/{id}")
    public ApiResponse<?> remove(@PathVariable Long id) {
        boolean re = assessResultService.removeById(id);
        return ApiResponse.success(re);
    }


    @ApiOperation(value = "查询记录")
    @PostMapping("/page")
    public ApiResponse<?> page(@RequestBody AssessResultPageReq pageReq) {

        Page<AssessResultResp> infoPage = assessResultService.pageList(pageReq);
        return ApiResponse.success(infoPage);
    }

    @ApiOperation(value = "查询用户个人记录")
    @PostMapping("/userPage")
    public ApiResponse<?> userPage(@RequestBody AssessResultPageReq pageReq) {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        pageReq.setUserId(userId);
        Page<AssessResultResp> infoPage = assessResultService.pageList(pageReq);
        return ApiResponse.success(infoPage);
    }

    @ApiOperation(value = "查询用户每月测试以及异常统计查询")
    @PostMapping("/userRecordList")
    public ApiResponse<?> userRecordList(@RequestBody RecordCountReq req) {
        List<List<Integer>> lists = assessResultService.recordCount(req);
        return ApiResponse.success(lists);
    }

    @ApiOperation(value = "获取年份")
    @GetMapping("/getYears")
    public ApiResponse<?> getYears() {
        List<Integer> re = assessResultService.getYears();
        return ApiResponse.success(re);
    }

}

