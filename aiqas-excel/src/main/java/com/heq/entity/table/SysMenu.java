package com.heq.entity.table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysMenu {

    @ApiModelProperty("菜单Id")
    private String id;
    @ApiModelProperty("父级菜单ID")
    private String parentId;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单链接地址")
    private String href;
    @ApiModelProperty("菜单图标")
    private String icon;
    @ApiModelProperty("菜单等级")
    private String menuLevel;
    @ApiModelProperty("菜单排序")
    private Long sort;

}
