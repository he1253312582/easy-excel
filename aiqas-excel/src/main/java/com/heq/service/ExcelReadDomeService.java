package com.heq.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.heq.comp.listener.DemoDataListener;
import com.heq.comp.listener.DemoHeadDataListener;
import com.heq.comp.listener.ExcelReadDomeListener;
import com.heq.entity.excel.ExcelDemoData;
import com.heq.entity.excel.ExcelDemoEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * Created user ： heqiang
 * created date : 2019/10/19 15:04
 * Description : No Description
 * version : 1.0
 */
@Slf4j
@Service("excelReadDomeService")
public class ExcelReadDomeService {


    private String filePath = "D:\\ideaWorkSpaces\\easyexcel\\repository\\excel\\read\\demoRead.xlsx";

    /**
     * 先使用监听器来读取数据，再使用doReadSync来读取数据
     *
     * @throws FileNotFoundException
     */
    public void excelRead1() throws FileNotFoundException {
        ExcelReaderBuilder readerBuilder = EasyExcel.read(new File(filePath));
        ExcelReaderSheetBuilder sheetBuilder = readerBuilder.sheet();
        sheetBuilder.autoTrim(true);//去除空格
        sheetBuilder.headRowNumber(1);//设置表头的行数
        sheetBuilder.sheetNo(0);//设置表格sheet；
        sheetBuilder.registerReadListener(new ExcelReadDomeListener());//注册一个监听器
        sheetBuilder.head(ExcelDemoEntity.class);
        List<Object> readList = sheetBuilder.doReadSync();
        readList.forEach(data -> {
            log.info("读取excel中的数据:{}", data);
        });
    }

    /**
     * 自定义监听的同步读取：
     * doReadSync内部含有一个自定义的监听器，直接返回一个无模板的数据
     * 使用监听器来读取大数据超过10000多的大数据
     * doRead：内部无自定义监听，需要手动指定一个监听器
     */
    @Test
    public void excelRead2() throws FileNotFoundException {

        ExcelReadDomeListener excelListener = new ExcelReadDomeListener();

        //写法一:
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filePath, ExcelDemoEntity.class, excelListener).sheet().doRead();

        // 写法2：
        ExcelReader excelReader = EasyExcel.read(filePath, ExcelDemoEntity.class, excelListener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();

        //写法3
        InputStream inputStream = new FileInputStream(filePath);
        List<Object> readList = EasyExcel.read(inputStream).sheet(0).headRowNumber(1).doReadSync();
        readList.forEach(e -> {
            System.out.println(e);
        });
    }


    /**
     * 方式一:
     * 同时读取多个sheet
     * 遍历sheet来读取数据
     * 这里使用的是同一个监听器
     */
    @Test
    public void repeatedRead() {
        ExcelReadDomeListener listener = new ExcelReadDomeListener();
        ExcelReader builder = EasyExcel.read(filePath).head(ExcelDemoEntity.class).registerReadListener(listener).headRowNumber(1).build();
        //sheet集合
        List<ReadSheet> sheets = builder.excelExecutor().sheetList();
        for (ReadSheet sheet : sheets) {
            listener.getAllList().clear();
            log.info("sheet name:{}", sheet.getSheetName());
            //设置标题数
            sheet.setHeadRowNumber(2);
            //读取每一个sheet的内容
            builder.read(sheet);
            List<ExcelDemoEntity> allList = listener.getAllList();
            log.info("{}中的数据为：{}", sheet.getSheetName(), allList);
        }
    }

    /**
     * 方式一:
     * 同时读取多个sheet
     * 遍历sheet来读取数据
     * 这里使用的是可以使用不同的监听器
     */
    @Test
    public void repeatedRead2() {
        ExcelReadDomeListener excelListener = new ExcelReadDomeListener();
        ExcelReader excelReader = EasyExcel.read(filePath).build();
        // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
        ReadSheet readSheet1 = EasyExcel.readSheet(0).head(ExcelDemoEntity.class).registerReadListener(new DemoDataListener()).build();
        ReadSheet readSheet2 = EasyExcel.readSheet(1).head(ExcelDemoEntity.class).registerReadListener(new ExcelReadDomeListener()).build();
        excelReader.read(readSheet1);
        excelReader.read(readSheet2);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }



    @Test
    public void headerRead() {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(filePath, ExcelDemoData.class, new DemoHeadDataListener()).sheet(0).headRowNumber(1).doRead();
    }



}
