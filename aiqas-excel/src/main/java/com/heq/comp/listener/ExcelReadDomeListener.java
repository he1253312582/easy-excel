package com.heq.comp.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.heq.entity.excel.ExcelDemoEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created user ： heqiang
 * created date : 2019/10/19 14:51
 * Description : 重写excel数据读取的监听器
 * version : 1.0
 */
@Slf4j
public class ExcelReadDomeListener extends AnalysisEventListener<ExcelDemoEntity> {
    private static final int BATCH_COUNT = 20;
    private static int count = 1;
    private List<ExcelDemoEntity> list = new ArrayList<ExcelDemoEntity>();
    private List<ExcelDemoEntity> allList = new ArrayList<ExcelDemoEntity>();

    public List<ExcelDemoEntity> getAllList() {
        return allList;
    }

    @Override
    public void invoke(ExcelDemoEntity entity, AnalysisContext context) {
        list.add(entity);
        count++;
        log.info("监听器中解析到的数据,当前第{}行数据为：{}!!!", count, entity);
        if (list.size() >= BATCH_COUNT) {
            log.info("调用保存数据的的行为!");
            allList.addAll(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext arg0) {
        log.info("所有数据解析完成!,共有{}条数据!!!", count);
    }


}
