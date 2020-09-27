package com.heq.entity.table;

import lombok.Data;

@Data
public class TemplateFormula {
    public TemplateFormula() {
    }

    public TemplateFormula(Long templateId, Long formulaId) {
        this.templateId = templateId;
        this.formulaId = formulaId;
    }

    private Long templateId;
    private Long formulaId;


    public String getString(Long templateId, Long formulaId) {

        return "TemplateFormula{" +
                "templateId=" + templateId +
                ", formulaId=" + formulaId +
                '}';
    }
}
