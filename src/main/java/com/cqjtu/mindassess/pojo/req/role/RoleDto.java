package com.cqjtu.mindassess.pojo.req.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.List;

@ApiModel("添加或修改角色对象参数")
@Data
public class RoleDto {


    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty("角色名")
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    @ApiModelProperty("该角色描述")
    @NotBlank(message = "对该角色的描述不能为空")
    private String description;

    @ApiModelProperty("角色状态,0启用，1禁用")
    @Range(min = 0,max = 1,message = "角色状态只能是0（禁用）或1(启用)")
    private Integer status;

    @ApiModelProperty("权限主键集合")
    List<Long> permissionIdList;
}
