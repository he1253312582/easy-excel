package com.heq.enums;

import io.swagger.annotations.ApiModelProperty;

public enum UserStatus implements EnumMessage{

    @ApiModelProperty("启用")
    USER_START,
    @ApiModelProperty("停用")
    USER_STOP,
    @ApiModelProperty("锁定")
    USER_LOCK
}
