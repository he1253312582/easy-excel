package com.heq.server;

import com.heq.service.ExcelEntityReadService;
import com.heq.service.FormulaNames;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created user ： heqiang
 * created date : 2019/10/19 2:29
 * Description : No Description
 * version : 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelReadTest {

    @Autowired
    private ExcelEntityReadService excelEntityReadService;


    @Test
//    @Transactional
    public void test1005() throws IOException {
        String filePath = "D:\\ideaWorkSpaces\\easyexcel\\repository\\excel\\read\\银行理财分类样本-1005.xlsx";
        excelEntityReadService.excelRead(filePath, FormulaNames.FORMULA_NAME_01, "银行理财场景");
    }

    @Test
//    @Transactional
    public void test1010() throws IOException {
        String filePath = "D:\\ideaWorkSpaces\\easyexcel\\repository\\excel\\read\\语音质检分类样本-1010.xlsx";
        excelEntityReadService.excelRead(filePath, FormulaNames.FORMULA_NAME_02, "黑势力催收场景");
    }

    @Test
//    @Transactional
    public void test1015() throws IOException {
        String filePath = "D:\\ideaWorkSpaces\\easyexcel\\repository\\excel\\read\\语音质检分类样本-1015.xlsx";
        excelEntityReadService.excelRead(filePath, FormulaNames.FORMULA_NAME_03, "蘑菇街测试4");
    }


}

