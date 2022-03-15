package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.annotation.NoIncludeSwagger;
import com.cqjtu.mindassess.common.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/testApi")
    public ApiResponse<?> testApi(int a){
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        return ApiResponse.success(list);
    }
}
