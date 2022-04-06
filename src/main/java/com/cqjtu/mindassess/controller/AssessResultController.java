package com.cqjtu.mindassess.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.pojo.req.AssessResultPageReq;
import com.cqjtu.mindassess.pojo.req.AssessResultReq;
import com.cqjtu.mindassess.pojo.vo.systeminfo.AssessResultInfo;
import com.cqjtu.mindassess.service.IAssessResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

        Page<AssessResultInfo> infoPage = assessResultService.pageList(pageReq);
        return ApiResponse.success(infoPage);
    }

}

