package com.heq.excel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * ExcelIgnoreUnannotated 在类上使用，使用了该注解，如果成员变量上没有 ExcelProperty属性，将被忽略该属性（以版不用）
 * HeadRowHeight 设置表头行高
 * ContentRowHeight 设置内容行高
 * ColumnWidth 设置列宽
 * ExcelProperty 用于设置表头
 * index 设置写入的顺序，从0开始由小到大，以此为从左到右对应
 * value 设置表头取值 一般和index分开使用
 * ExcelIgnore 让excel忽略该列
 * DateTimeFormat 日期格式化
 */

@Data
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@HeadRowHeight(30)
@ContentRowHeight(20)
public class Student {

    @ExcelIgnore
    @ColumnWidth(20)
    @ExcelProperty(value = "学生Id")
    private String stuId;

    @NumberFormat("00000000")
    @ColumnWidth(20)
    @ExcelProperty(value = "学生编号")
    private int stuNo;

    @ColumnWidth(20)

//    @ExcelProperty(value = {"学生信息", "学生姓名"})
    @ExcelProperty(value = {"学生姓名"})
    private String userName;

    @ColumnWidth(20)
//    @ExcelProperty(value = {"学生信息", "学生性别"})
    @ExcelProperty(value = {"学生性别"})
    private String gender;


    @DateTimeFormat("yyyy-MM-dd")
    @ColumnWidth(20)
//    @ExcelProperty(value = {"学生信息", "学生生日"})
    @ExcelProperty(value = {"学生生日"})
    private Date birthday;

}
