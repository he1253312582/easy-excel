package com.heq.excel.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.heq.excel.entity.ExcelProduct;
import com.heq.excel.entity.FlowInstance;
import com.heq.excel.entity.IpoProduct;
import com.heq.excel.entity.IpoStockCalendar;
import com.heq.excel.listener.ExcelProductListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelProductServiceTest {

    @Autowired
    private IpoProductServiceImpl productService;

    @Autowired
    private IpoStockCalendarServiceImpl stockCalendarService;

    @Autowired
    private FlowInstanceServiceImpl instanceService;

    @Autowired
    private FlowActivityServiceImpl activityService;

    private static final int PRODUCT_TYPE_SH = 1;
    private static final int PRODUCT_TYPE_SZ = 2;
    private static final int PRODUCT_TYPE_SH_SZ = 3;
    private static final int STOCK_MARKET_TYPE_SH = 1;
    private static final int STOCK_MARKET_TYPE_SZ = 2;

    @Test
    public void test01() throws FileNotFoundException {
        String filePath = "F:\\WorkSpace\\LocalWork\\exercise\\EasyExcel\\doc\\EasyExcel\\配售对象市值数据-模板.xlsx";
        String missFilePath = "F:\\WorkSpace\\LocalWork\\exercise\\EasyExcel\\doc\\EasyExcel\\配售对象市值数据-miss.xlsx";

        ExcelReaderBuilder readerBuilder = null;
        Map<String, ExcelProduct> productMapSH = new HashMap<>();
        Map<String, ExcelProduct> productMapSZ = new HashMap<>();
        Map<String, ExcelProduct> productMapSH_SZ = new HashMap<>();

        readerBuilder = EasyExcel.read(filePath, ExcelProduct.class, new ExcelProductListener(PRODUCT_TYPE_SH,"沪市单边", productMapSH));
        ExcelReaderSheetBuilder sheet = readerBuilder.sheet("沪市单边");
        sheet.headRowNumber(2);
        sheet.doRead();

        readerBuilder = EasyExcel.read(filePath, ExcelProduct.class, new ExcelProductListener(PRODUCT_TYPE_SZ,"深市单边", productMapSZ));
        sheet = readerBuilder.sheet("深市单边");
        sheet.headRowNumber(2);
        sheet.doRead();

        readerBuilder = EasyExcel.read(filePath, ExcelProduct.class, new ExcelProductListener(PRODUCT_TYPE_SH_SZ,"沪深双边", productMapSH_SZ));
        sheet = readerBuilder.sheet("沪深双边");
        sheet.headRowNumber(2);
        sheet.doRead();

        Set<String> productNameSetSH = productMapSH.keySet();
        Set<String> productNameSetSZ = productMapSZ.keySet();
        Set<String> productNameSetSH_SZ = productMapSH_SZ.keySet();

        log.info("查询产品,并根据Excel中的数据进行更新");
        List<IpoProduct> productListSH = productService.queryProductByNameList(productNameSetSH);
        List<IpoProduct> productListSZ = productService.queryProductByNameList(productNameSetSZ);
        List<IpoProduct> productListSH_SZ = productService.queryProductByNameList(productNameSetSH_SZ);

        List<ExcelProduct> missProductList =new ArrayList<>();
        log.info("记录缺失的产品信息~~回写到excel表格中~~");
        if (productMapSH.size() != productListSH.size()) {
            List<ExcelProduct> excelProductList = productService.filterNotDBProduct(productListSH, productMapSH);
            missProductList.addAll(excelProductList);
        }
        if (productMapSZ.size() != productListSZ.size()) {
            List<ExcelProduct> excelProductList = productService.filterNotDBProduct(productListSZ, productMapSZ);
            missProductList.addAll(excelProductList);
        }
        if (productMapSH_SZ.size() != productListSH_SZ.size()) {
            List<ExcelProduct> excelProductList = productService.filterNotDBProduct(productListSH_SZ, productMapSH_SZ);
            missProductList.addAll(excelProductList);
        }
        excelWriter(missFilePath,missProductList);

        log.info("修改产品的协会编码！！！");
        productService.updateProduct(productListSH, productMapSH);
        productService.updateProduct(productListSZ, productMapSZ);
        productService.updateProduct(productListSH_SZ, productMapSH_SZ);

        log.info("查询当天询价的股票信息！！");
        List<IpoStockCalendar> stockList = stockCalendarService.queryStockForInquiry();
        List<IpoProduct> productAllList = new ArrayList<>();
        for (IpoStockCalendar stockCalendar : stockList) {
            log.info("开始执行{}---{}股票实例.....");
            if (stockCalendar.getMarketType() == STOCK_MARKET_TYPE_SH) {
                productAllList.addAll(productListSH);
                productAllList.addAll(productListSH_SZ);
                List<FlowInstance> instanceList = instanceService.instanceComp(productAllList, stockCalendar);
                activityService.batchInsertFlowInstance(instanceList);
            } else if (stockCalendar.getMarketType() == STOCK_MARKET_TYPE_SZ) {
                productAllList.addAll(productListSH_SZ);
                productAllList.addAll(productListSH_SZ);
                List<FlowInstance> instanceList = instanceService.instanceComp(productAllList, stockCalendar);
                activityService.batchInsertFlowInstance(instanceList);
            } else {
                throw new RuntimeException("[{}]--股票数据异常，请核查！");
            }
        }

    }

    private void excelWriter(String filePath, List<ExcelProduct> excelProductList) throws FileNotFoundException {
        ExcelWriterBuilder writerBuilder = EasyExcel.write(filePath, ExcelProduct.class);
        ExcelWriterSheetBuilder sheetBuilder = writerBuilder.sheet("数据库缺失产品").sheetNo(4);
        sheetBuilder.doWrite(excelProductList);
    }

    /**
     * 多次写入
     * @param filePath
     * @param excelProductList
     * @throws FileNotFoundException
     */
    private void excelWriter2(String filePath, List<ExcelProduct> excelProductList) throws FileNotFoundException {
        // 方法1 如果写到同一个sheet
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(filePath, ExcelProduct.class).build();
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("数据库缺失产品").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 5; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                excelWriter.write(excelProductList, writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

}