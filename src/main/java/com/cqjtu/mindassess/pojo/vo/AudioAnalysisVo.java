package com.cqjtu.mindassess.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 音频分析Vo对象
 * @Author: amosdzhn
 * @CreateTime: 2022/5/7 16:12
 */
@ApiModel("音频文件分析Vo对象")
@Data
public class AudioAnalysisVo {

    @ApiModelProperty("音频文件url")
    private String audioUrl;
    @ApiModelProperty("音频文件分析情绪")
    private String audioEmotion;
    @ApiModelProperty("音频文件的对应文本")
    private String audioText;
    @ApiModelProperty("音频文件对应的文本情绪")
    private String textEmotion;

}
