package com.cqjtu.mindassess.pojo.resp.assess;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangzhencheng
 */
@Data
public class AssessResultResp {
    /**
     * 主键
     */
    private Long id;

    /**
     * 关联userid
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 测试类型
     */
    private String assessType;

    /**
     * 程度，0，正常；1轻度；2，中度；3，重度
     */
    private Integer resultLevel;

    /**
     * 测试时间，年
     */
    private Integer year;

    /**
     * 月
     */
    private Integer month;

    /**
     * 日
     */
    private Integer day;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private LocalDateTime createTime;
}
