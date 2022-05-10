package com.cqjtu.mindassess.pojo.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: amosdzhn
 * @CreateTime: 2022/5/9 22:00
 */
@Data
public class TextEmotionResult {
    private Long words;
    private Long sentences;
    private Long good;
    private Long happy;
    private Long decline;
    private Long angry;
    private Long fear;
    private Long evil;
    private Long surprise;
}
