package com.heq.mapper;

import com.heq.entity.table.FormulaInfo;
import com.heq.entity.table.ScenesInfo;
import com.heq.entity.table.TemplateFormula;
import com.heq.entity.table.TemplateInfo;
import com.heq.mode.InformationMode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created user ： heqiang
 * created date : 2019/10/20 0:33
 * Description : No Description
 * version : 1.0
 */
@Repository
@Mapper
public interface InformationMapper {

    /**
     * 添加场景数据
     *
     * @param scenesInfo
     * @return
     */
    Long addScenesInfo(ScenesInfo scenesInfo);

    /**
     * 添加算子信息
     *
     * @param formulas
     * @return
     */
    Long addFormulaInfo(FormulaInfo formulas);
    Long addFormulaList(List<FormulaInfo> formulasList);

    /**
     * 添加模板信息
     *
     * @param templateInfo
     * @return
     */
    Long addTemplateInfo(TemplateInfo templateInfo);

    /**
     * 添加模板与算子的关联信息(一个模板可以同时命中多个算子)
     *
     * @param list
     * @return
     */
    long addTemplateFormula(List<TemplateFormula> list);

    /**
     * 语句-场景-算子-关联查询
     * @return
     */
    List<InformationMode> queryTemplateAll();


}
