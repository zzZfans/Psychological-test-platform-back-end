package com.cqjtu.mindassess.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqjtu.mindassess.config.aliyunivconf.AliyunIVClient;
import com.cqjtu.mindassess.config.aliyunivconf.IVResponse;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.exception.SystemErrorException;
import com.cqjtu.mindassess.pojo.vo.AudioAnalysisVo;
import com.cqjtu.mindassess.service.IAudioFileAnalysisService;
import com.cqjtu.mindassess.service.ICallPyScriptService;
import com.cqjtu.mindassess.service.IFileService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Description: 音频文件分析服务实现类
 * @Author: amosdzhn
 * @CreateTime: 2022/5/7 16:16
 */
@Service
public class AudioFileAnalysisServiceImpl implements IAudioFileAnalysisService {

    @Resource
    AliyunIVClient ivClient;
    @Resource
    IFileService fileService;
    @Resource
    ICallPyScriptService callPyScriptService;


    @Override
    public AudioAnalysisVo analysisAudioFile(MultipartFile file) {
        AudioAnalysisVo vo = new AudioAnalysisVo();

        // 异步分析
        Future<String> emotionFuture = null;
        InputStream is = null;
        try {
            is = file.getInputStream();
            emotionFuture = callPyScriptService.callEmotionRecognition(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 保存在MinIO
        String audioUrl = fileService.fileUpload(file);
        vo.setAudioUrl(audioUrl);
        String taskId = ivClient.submitFileTransRequest(audioUrl);
        List<IVResponse> sentences = ivClient.getFileTranResult(taskId);
        String audioText = sentences.stream().map(IVResponse::getText).collect(Collectors.joining());
        if(ObjectUtils.isEmpty(sentences)){
            audioText = "";
        }
        vo.setAudioText(audioText);
        String emotion = null;
        try {
            String result = emotionFuture.get();
            //解析
            String[] scriptPrintLines = result.split("\n");
            emotion = scriptPrintLines[scriptPrintLines.length - 3].split(":")[1].trim();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new SystemErrorException("调用Python脚本解析音频文件情绪错误");
        }
        vo.setAudioEmotion(emotion);
        vo.setTextEmotion(emotion);
        return vo;
    }
}
