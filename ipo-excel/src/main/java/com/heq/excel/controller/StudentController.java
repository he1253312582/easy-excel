package com.heq.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.heq.excel.entity.Student;
import com.heq.excel.listener.StudentListener;
import com.heq.excel.service.StudentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
@Slf4j
@RestController
@RequestMapping("/student")
@Api(value = "Excel文件上传", description = "Excel文件上传")
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private StudentListener studentListener;


    @ResponseBody
    @PostMapping("/readExcel")
    public String readExcel(MultipartFile excelFile) {
        try {
            InputStream inputStream = excelFile.getInputStream();
            //工作簿
            ExcelReaderBuilder readerBuilder = EasyExcel.read(inputStream, Student.class, studentListener);
            readerBuilder.autoCloseStream(true);
            //工作表
            ExcelReaderSheetBuilder sheetBuilder = readerBuilder.sheet(0);
            sheetBuilder.autoTrim(true);
            //读取内容
            sheetBuilder.doRead();
            return "SUCCESS";
        } catch (IOException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }


    // 上传
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        String fileName = URLEncoder.encode("学生信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");
        //响应的输入流
        ServletOutputStream outputStream = response.getOutputStream();
        // workbook
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class);

        ArrayList<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < 15; i++) {
            studentList.add(new Student("1000" + i, 1000 + i, "张xx" + i, "男", new Date()));
        }
        // sheet
        writeWorkBook.sheet().doWrite(studentList);
    }


}
