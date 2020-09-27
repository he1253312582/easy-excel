package com.heq.excel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.sun.org.glassfish.gmbal.Description;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@HeadRowHeight(30)
@ContentRowHeight(20)
public class ExcelProduct {

    @ColumnWidth(20)
    @ExcelProperty(value = "序号")
    private int prodIndex;

    @ColumnWidth(20)
    @ExcelProperty(value = "协会编号")
    private String societyCode;

    @ColumnWidth(30)
    @ExcelProperty(value = "配售对象")
    private String productName;

    @ExcelIgnore
    @ColumnWidth(20)
    @Description(value = "沪深市类型：1.沪市;2.深市;3.沪深双边")
    private int allowStockType;

    @ColumnWidth(30)
    @ExcelProperty(value = "沪深市类型")
    private String allowStockTypeName;
}
