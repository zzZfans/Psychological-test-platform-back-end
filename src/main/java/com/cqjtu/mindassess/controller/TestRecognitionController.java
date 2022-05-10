package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.service.ICallPyScriptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: amosdzhn
 * @CreateTime: 2022/5/9 22:13
 */
@Api(tags = {"文本识别控制器"})
@RestController
@RequestMapping("/text")
public class TestRecognitionController {

    @Resource
    ICallPyScriptService callPyScriptService;

    @ApiOperation("文本情绪分析")
    @PostMapping("/analysis")
    public ApiResponse<?> textEmotionRecognition(String text){
        InputStream is = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));;
        String emotion = callPyScriptService.callTextEmotionRecognition(is);
        return ApiResponse.success(emotion);
    }
}
