package com.cqjtu.mindassess.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.Future;

/**
 * @Description: 调用Python脚本服务
 * @Author: amosdzhn
 * @CreateTime: 2022/5/8 10:36
 */
public interface ICallPyScriptService {

    /**
     * 异步任务，调用Python脚本进行音频情绪识别
     * @param is 被识别音频输入流
     * @return -
     */
    Future<String> callEmotionRecognition(InputStream is);

    /**
     * 调用Python脚本进行文本情绪识别
     * @param is 被识别文本输入流
     * @return -
     */
    String callTextEmotionRecognition(InputStream is);
}
