package com.heq.excel.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.heq.excel.entity.Student;
import com.heq.excel.listener.StudentListener;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StudentServiceTest {


    private List<Student> studentList;

    /**
     * 读取excel文档
     * 1.获取工作簿
     * 2.读取工作表
     */
    @Test
    public void test01() {
        /**
         * @param file 文件路径
         * @param head 文件中每一行数据存储对应的实体对象
         * @param readListener 读监听器，每一行内容都会掉一次该对象的invoke方法，可在invoke中操作读取到的数据
         */
        String filePath = "F:\\WorkSpace\\LocalWork\\exercise\\EasyExcel\\src\\main\\resources\\excel\\学生表格.xlsx";
        Class class_z = Student.class;
        ReadListener listener = new StudentListener();
        //获取一个工作簿对象
        ExcelReaderBuilder readerBuilder = EasyExcel.read(filePath, class_z, listener);
        //获取工作表对象
        ExcelReaderSheetBuilder sheet = readerBuilder.sheet(0);
        sheet.head(Student.class);
        sheet.headRowNumber(1);
        sheet.registerReadListener(listener);
        //读取工作表中的内容
        sheet.doRead();

    }

    /**
     * 写入excel文档
     */
    @Test
    public void test02() {
        String filePath = "F:\\WorkSpace\\LocalWork\\exercise\\EasyExcel\\src\\main\\resources\\excel\\学生表格-write.xlsx";
        ExcelWriterBuilder writerBuilder = EasyExcel.write(filePath, Student.class);
        ExcelWriterSheetBuilder sheetBuilder = writerBuilder.sheet(0);
        sheetBuilder.doWrite(studentList);
    }

    @Before
    public void initData() {
        this.studentList =new ArrayList<Student>();
        for (int i = 0; i < 15; i++) {
            this.studentList.add(new Student("1000"+i,1000+i,"张xx"+i,"男",new Date()));
        }
    }


}