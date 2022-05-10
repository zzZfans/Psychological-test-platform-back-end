package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.pojo.vo.AudioAnalysisVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 音频文件分析服务
 * @Author: amosdzhn
 * @CreateTime: 2022/5/7 16:10
 */
public interface IAudioFileAnalysisService {


    /**
     * 音频文件分析
     * @param file 音频文件
     * @return -
     */
    AudioAnalysisVo analysisAudioFile(MultipartFile file);
}
