package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.pojo.vo.AudioAnalysisVo;
import com.cqjtu.mindassess.service.IAudioFileAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description: 音频识别控制器
 * @Author: amosdzhn
 * @CreateTime: 2022/5/7 15:57
 */
@Api(tags = {"阿里云音频识别控制器"})
@RestController
@RequestMapping("/audio")
public class AudioRecognitionController {

    @Resource
    IAudioFileAnalysisService audioFileAnalysisService;

    /**
     * 1.将上传的文件上载到对象存储
     * 2.调用阿里云语音服务进行音频分析
     * @param multipartFile
     * @return
     */
    @ApiOperation("音频转文本及音频/文本请情绪")
    @LogOperation("音频转文本及音频/文本请情绪")
    @PostMapping("/analysis")
    public ApiResponse<?> audioAnalysis(MultipartFile multipartFile){
        if(ObjectUtils.isEmpty(multipartFile)){
            return ApiResponse.fail(200,"文件为空");
        }
        AudioAnalysisVo audioAnalysisVo = audioFileAnalysisService.analysisAudioFile(multipartFile);
        return ApiResponse.success(audioAnalysisVo);
    }
}
