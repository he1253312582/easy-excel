package com.heq.entity.table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 角色对象
 */
@Data
public class SysRole {

    @ApiModelProperty("角色ID")
    private String id;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色说明")
    private String description;
    @ApiModelProperty("该角色所拥有的的菜单")
    private Set<SysMenu> menuSet;

}
