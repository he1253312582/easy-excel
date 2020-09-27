package com.heq.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.heq.excel.entity.ExcelProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import java.util.Map;

@Slf4j
@Scope("prototype")
public class ExcelProductListener extends AnalysisEventListener<ExcelProduct> {


    private int stockType;
    private String stockTypeName;
    private Map<String, ExcelProduct> productMap;

    public ExcelProductListener(int stockType, String stockTypeName, Map<String, ExcelProduct> productMap) {
        this.stockType = stockType;
        this.stockTypeName = stockTypeName;
        this.productMap = productMap;
    }

    @Override
    public void invoke(ExcelProduct product, AnalysisContext context) {
        product.setAllowStockType(this.stockType);
        product.setAllowStockTypeName(this.stockTypeName);
        this.productMap.put(product.getProductName(), product);
        log.info("正在读取产品---[{}]", product);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("doAfterAllAnalysed...........");
    }
}
