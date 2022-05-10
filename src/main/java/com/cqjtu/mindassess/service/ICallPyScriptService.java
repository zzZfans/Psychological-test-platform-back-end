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

    Future<String> callEmotionRecognition(InputStream is);
}
