package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.service.ICallPyScriptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: zhangning
 * @CreateTime: 2022/5/9 22:13
 */
@Api(tags = {"文本情绪分析控制器"})
@RestController
@RequestMapping("/text")
public class TestRecognitionController {

    @Resource
    ICallPyScriptService callPyScriptService;

    @ApiOperation("文本情绪分析")
    @LogOperation("文本情绪分析")
    @PostMapping("/analysis")
    public ApiResponse<?> textEmotionRecognition(@RequestBody String text){
        if(ObjectUtils.isEmpty(text)){
            return ApiResponse.fail(200,"文本不能未空");
        }
        InputStream is = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));;
        String emotion = callPyScriptService.callTextEmotionRecognition(is);
        return ApiResponse.success(emotion);
    }
}
