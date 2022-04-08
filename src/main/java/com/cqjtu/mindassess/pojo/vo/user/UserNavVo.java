package com.cqjtu.mindassess.pojo.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户导航栏信息")
@Data
public class UserNavVo {

    @ApiModelProperty("Vue组件名,对应数据库component_name")
    private String name;

    @ApiModelProperty("父组件id")
    private Long parentId;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("Vue组件")
    private String component;

    @ApiModelProperty("重定向路径")
    private String redirect;

    @ApiModelProperty("meta")
    private Meta meta;

    @ApiModel("meta")
    @Data
    public static class Meta{
        @ApiModelProperty("菜单图标")
        private String icon;
        @ApiModelProperty("")
        private String title;
    }
}
