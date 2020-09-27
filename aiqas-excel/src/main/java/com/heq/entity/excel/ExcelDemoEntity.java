package com.heq.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Description;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Description("解读excelDome")
public class ExcelDemoEntity {
    /**
     * 1.一般只index和value不要同时使用
     * 2.只有添加了注解的属性才会赋值
     */
    @ExcelProperty(index = 0,value = "字符串标题")
    private String text;
    @ExcelProperty(index = 1,value = "日期标题")
    public Date date;
    @ExcelProperty(index = 2,value = "数字标题")
    private Double number;



}
