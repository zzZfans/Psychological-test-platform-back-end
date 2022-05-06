package com.cqjtu.mindassess.service;

/**
 * @author zhangning
 */
public interface IAsrtService {

    /**
     * 识别音频为中文,采样率必须小于16000
     * @return - 中文字符串
     */
    public String recognizeSpeechToChinese(byte[] audio);
}
