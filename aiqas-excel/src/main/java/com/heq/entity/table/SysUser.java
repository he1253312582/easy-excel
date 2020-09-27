package com.heq.entity.table;


import com.heq.enums.UserStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
public class SysUser {
    public SysUser() {
    }

    public SysUser(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }


    @ApiModelProperty("用户Id")
    private String id;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户密码")
    private String passWord;
    @ApiModelProperty("账号状态")
    @Enumerated(EnumType.STRING)
    private UserStatus accountStatus;
    @ApiModelProperty("该用户所拥有的的角色")
    private Set<SysRole> roleSet;

}
