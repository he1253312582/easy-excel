package com.heq.server;

import com.heq.entity.table.FormulaInfo;
import com.heq.entity.table.ScenesInfo;
import com.heq.entity.table.TemplateFormula;
import com.heq.entity.table.TemplateInfo;
import com.heq.mapper.InformationMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created user ： heqiang
 * created date : 2019/10/20 2:04
 * Description : No Description
 * version : 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InformationMapperTest {

    @Autowired
    private InformationMapper mapper;

    @Test
    public void test_ScenesInfo() {
        ScenesInfo scenesInfo = new ScenesInfo("测试场景", "测试场景","test004");
        mapper.addScenesInfo(scenesInfo);
        log.info("场景ID为[{}]", scenesInfo.getId());
    }

    @Test
    public void test_FormulaInfo() {
        FormulaInfo formulaInfo = new FormulaInfo("测试算子",  UUID.randomUUID().toString().replaceAll("-", ""),17L);
        mapper.addFormulaInfo(formulaInfo);
        log.info("算子ID为[{}]", formulaInfo.getId());
    }

    @Test
    public void test_TemplateInfo() {
        TemplateInfo templateInfo = new TemplateInfo("测试模板语句", 15);
        mapper.addTemplateInfo(templateInfo);
        log.info("算子ID为[{}]", templateInfo.getId());
    }


    @Test
    public void test_TemplateFormula() {
        List<TemplateFormula> list = new ArrayList<>();
        list.add(new TemplateFormula(310L, 13L));
        list.add(new TemplateFormula(310L, 14L));
        list.add(new TemplateFormula(3309L, 13L));
        list.add(new TemplateFormula(3309L, 14L));
        Long count = mapper.addTemplateFormula(list);
        log.info("设置算子和模板的关系，操作了{}条语句!",count);
    }
}
