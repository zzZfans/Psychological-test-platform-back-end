package com.cqjtu.mindassess.pojo.vo.subject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: amosdzhn
 * @CreateTime: 2022/5/12 16:41
 */
@Data
@ApiModel("文本测试题目")
public class TextSubjectVo {

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("题目")
    private List<TextSubject> subjects;
}