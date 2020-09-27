package com.heq.mode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="InformationMode",description="Information模板查询对象")
public class InformationMode {
    @ApiModelProperty("对话语句")
    private String sentence;
    @ApiModelProperty("质检算子")
    private String operator;
    @ApiModelProperty("语句场景")
    private String scenes;


}
