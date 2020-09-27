package com.heq.enums;

import io.swagger.annotations.ApiModelProperty;

public enum Whether implements EnumMessage{

    @ApiModelProperty(notes = "是")
    YES,

    @ApiModelProperty(notes = "否")
    NO
}
