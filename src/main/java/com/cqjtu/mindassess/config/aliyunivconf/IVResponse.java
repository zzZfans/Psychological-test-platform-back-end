package com.cqjtu.mindassess.config.aliyunivconf;

import lombok.Data;

/**
 * @Description: 用于解析阿里云音视频服务响应的JSON解析
 * @Author: amosdzhn
 * @CreateTime: 2022/5/8 8:35
 */
@Data
public class IVResponse {

    private Long EndTime;
    private Long SilenceDuration;
    private Long BeginTime;
    private String Text;
    private Byte ChannelId;
    private Integer SpeechRate;
    private Float EmotionValue;
}
