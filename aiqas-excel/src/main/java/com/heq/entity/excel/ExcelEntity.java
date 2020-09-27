package com.heq.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Description;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Description("用于excel数据的公用实体对象")
public class ExcelEntity {


    @ExcelProperty(index = 0,value = "序号")
    public Long order;
    @ExcelProperty(index = 1,value = "样本")
    public String sample;
    @ExcelProperty(index = 2)
    public Integer class1;
    @ExcelProperty(index = 3)
    public Integer class2;
    @ExcelProperty(index = 4)
    public Integer class3;
    @ExcelProperty(index = 5)
    public Integer class4;
    @ExcelProperty(index = 6)
    public Integer class5;
    @ExcelProperty(index = 7)
    public Integer class6;
    @ExcelProperty(index = 8)
    public Integer class7;
    @ExcelProperty(index = 9)
    public Integer class8;
    @ExcelProperty(index = 10)
    public Integer class9;
    @ExcelProperty(index = 11)
    public Integer class10;
    @ExcelProperty(index = 12)
    public Integer class11;
    @ExcelProperty(index = 13)
    public Integer class12;
    @ExcelProperty(index = 14)
    public Integer class13;
    @ExcelProperty(index = 15)
    public Integer class14;
    @ExcelProperty(index = 16)
    public Integer class15;
    @ExcelProperty(index = 17)
    public Integer class16;
    @ExcelProperty(index = 18)
    public Integer class17;
    @ExcelProperty(index = 19)
    public Integer class18;
    @ExcelProperty(index = 20)
    public Integer class19;
    @ExcelProperty(index = 21)
    public Integer class20;
    @ExcelProperty(index = 22)
    public Integer class21;
    @ExcelProperty(index = 23)
    public Integer class22;
    @ExcelProperty(index = 24)
    public Integer class23;
    @ExcelProperty(index = 25)
    public Integer class24;
    @ExcelProperty(index = 26)
    public Integer class25;
    @ExcelProperty(index = 27)
    public Integer class26;
    @ExcelProperty(index = 28)
    public Integer class27;
    @ExcelProperty(index = 29)
    public Integer class28;
    @ExcelProperty(index = 30)
    public Integer class29;
    @ExcelProperty(index = 31)
    public Integer class30;
    @ExcelProperty(index = 32)
    public Integer class31;
    @ExcelProperty(index = 33)
    public Integer class32;
    @ExcelProperty(index = 34)
    public Integer class33;
    @ExcelProperty(index = 35)
    public Integer class34;
    @ExcelProperty(index = 36)
    public Integer class35;
    @ExcelProperty(index = 37)
    public Integer class36;
    @ExcelProperty(index = 38)
    public Integer class37;
    @ExcelProperty(index = 39)
    public Integer class38;
    @ExcelProperty(index = 40)
    public Integer class39;
    @ExcelProperty(index = 41)
    public Integer class40;
    @ExcelProperty(index = 42)
    public Integer class41;
    @ExcelProperty(index = 43)
    public Integer class42;
    @ExcelProperty(index = 44)
    public Integer class43;
    @ExcelProperty(index = 45)
    public Integer class44;
    @ExcelProperty(index = 46)
    public Integer class45;

}
