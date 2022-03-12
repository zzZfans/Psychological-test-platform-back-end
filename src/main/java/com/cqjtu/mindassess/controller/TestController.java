package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "ok";
    }

    @GetMapping("/testApi")
    public ApiResponse<?> testApi(){
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        return ApiResponse.success(list);
    }
}
