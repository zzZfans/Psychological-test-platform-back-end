package com.cqjtu.mindassess.pojo.req.notice;

import com.cqjtu.mindassess.common.SaveGroup;
import com.cqjtu.mindassess.common.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author zhangzhencheng
 */

@Data
public class NoticeReq {
    @ApiModelProperty("公告id")
    @NotNull(message = "修改公告主键不能为空", groups = {UpdateGroup.class})
    @Null(message = "添加公告主键必须为空", groups = {SaveGroup.class})
    private Long id;

    @ApiModelProperty("公告标题")
    @NotBlank(message = "公告标题不能为空", groups = {SaveGroup.class, UpdateGroup.class})
    private String noticeTitle;

    @ApiModelProperty("公告内容")
    @NotBlank(message = "公告内容不能为空", groups = {SaveGroup.class, UpdateGroup.class})
    private String noticeContent;
}
