package com.heq.enums;

import io.swagger.annotations.ApiModelProperty;

public enum Sex implements EnumMessage{
    @ApiModelProperty("男")
    MALE,
    @ApiModelProperty("女")
    FEMALE
}
