package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.annotation.NoIncludeSwagger;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.pojo.vo.UserReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangning
 *
 * 测试使用
 */
@Api(tags = {"测试类","测试tags"},description = "测试description")
@RestController
public class TestController {

    @NoIncludeSwagger
    @GetMapping("/test")
    public String test(){
        return "ok";
    }

    @ApiOperation(value = "@ApiOperation.value",notes = "@ApiOperation.notes")
    @PostMapping("/testApi")
    public ApiResponse<?> testApi(@ApiParam(name = "name" , value = "value",required = true) int a,
                                  @ApiParam(name = "@ApiParam.name",value = "@ApiParam.value" ,required = true) @RequestBody UserReq userReq){
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        return ApiResponse.success(list);
    }
}
