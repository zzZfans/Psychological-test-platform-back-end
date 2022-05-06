package com.cqjtu.mindassess.controller;

import cn.hutool.core.util.ObjectUtil;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.service.IAsrtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangning
 */
@Api(tags = {"音频转换文本控制器"})
@RestController
@RequestMapping("/asrt/convert")
public class AsrtController {

    @Resource
    IAsrtService asrtService;


    @ApiOperation("音频文件转换为文本")
    @PostMapping("/audioFileToText")
    public ApiResponse<?> audioFileToText(@RequestParam("audioFile")MultipartFile file) {
        if(ObjectUtils.isEmpty(file)){
            return ApiResponse.fail(200,"音频文件不能为空");
        }
        if( !"audio/wav".equals(file.getContentType())){
            return ApiResponse.fail(200,"目前只支持 wav 格式的文件");
            //TODO 音频文件格式转换
        }
        String text = null;
        try {
            text = asrtService.recognizeSpeechToChinese(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.fail(200,"转换失败");
        }
        Map<String,String> map = new HashMap<>();
        map.put("text",text);
        return ApiResponse.success(map);
    }
}
