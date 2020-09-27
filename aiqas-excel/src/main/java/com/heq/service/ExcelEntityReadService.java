package com.heq.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.heq.comp.listener.ExcelEntityListener;
import com.heq.entity.excel.ExcelEntity;
import com.heq.entity.table.FormulaInfo;
import com.heq.entity.table.ScenesInfo;
import com.heq.entity.table.TemplateFormula;
import com.heq.entity.table.TemplateInfo;
import com.heq.mapper.InformationMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Transactional
@Service("excelEntityReadService")
public class ExcelEntityReadService {

    @Autowired
    private InformationMapper mapper;

    /**
     * 先使用监听器来读取数据，再使用doReadSync来读取数据
     *
     * @throws FileNotFoundException
     */
    public void excelRead(String filePath, String[] formula_names, String scenesName) throws IOException {
        FileInputStream stream = new FileInputStream(filePath);
        ExcelEntityListener listener = new ExcelEntityListener(mapper);
        ExcelReaderBuilder readerBuilder = EasyExcelFactory.read(stream, ExcelEntity.class, listener);
        ExcelReaderSheetBuilder sheet = readerBuilder.sheet(0).headRowNumber(2);
        sheet.doRead();
        if (stream != null) stream.close();
        saveData(listener.getExcelEntityData(), formula_names, scenesName);
    }

    public void saveData(List<ExcelEntity> excelEntityList, String[] formula_names, String scenesName) {
        //保存大场景
        String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        ScenesInfo scenes_info = new ScenesInfo(scenesName, scenesName,"Y-"+nowTime);
        mapper.addScenesInfo(scenes_info);
        //初始化算子信息
        List<FormulaInfo> formulaInfos = new ArrayList<>();
        for (int i = 0; i < formula_names.length; i++) {
            formulaInfos.add(new FormulaInfo(formula_names[i], "Y_" +nowTime+ StringUtils.leftPad(""+i,4,"0"), scenes_info.getId()));
        }
        //保存算子信息
        mapper.addFormulaList(formulaInfos);
        List<TemplateFormula> result = new ArrayList<>();
        excelEntityList.forEach(excelEntity -> {
            //保存模板信息
            TemplateInfo template = new TemplateInfo(excelEntity.sample, scenes_info.getId());
            mapper.addTemplateInfo(template);
            try {
                for (int i = 0; i < formulaInfos.size(); i++) {
                    Method method = excelEntity.getClass().getMethod("getClass" + (i + 1));
                    Integer val = (Integer) method.invoke(excelEntity);
                    if (val == null || val != 1) continue;
                    result.add(new TemplateFormula(template.getId(), formulaInfos.get(i).getId()));
                    if (result.size() > 0) mapper.addTemplateFormula(result);
                    log.info("【{}】共命中了{}条算子", excelEntity.sample, result.size());
                    result.clear();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }


}

