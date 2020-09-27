package com.heq.comp.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.heq.entity.excel.ExcelEntity;
import com.heq.mapper.InformationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created user ： heqiang
 * created date : 2019/10/20 3:01
 * Description : No Description
 * version : 1.0
 */
@Slf4j
public class ExcelEntityListener extends AnalysisEventListener<ExcelEntity> {
    private List<ExcelEntity> ExcelEntityData = new ArrayList<>();

    public ExcelEntityListener(InformationMapper mapper) {
        this.mapper = mapper;
    }

    //调用该mapper来操作数据库表
    private InformationMapper mapper;

    @Override
    public void invoke(ExcelEntity excelEntity, AnalysisContext context) {
        this.ExcelEntityData.add(excelEntity);
        log.info("当前读取第{}条数据为:{}", ExcelEntityData.size(),excelEntity);
    }

    public void doAfterAllAnalysed(AnalysisContext context) {
        Integer headRowNumber = context.readSheetHolder().getReadSheet().getHeadRowNumber();
        log.info("跳过{}行表头读取excel数据!!", headRowNumber);
        log.info("excel数据解读完成,共计{}条数据！！！", ExcelEntityData.size());
    }

    public List<ExcelEntity> getExcelEntityData() {
        return ExcelEntityData;
    }
}
