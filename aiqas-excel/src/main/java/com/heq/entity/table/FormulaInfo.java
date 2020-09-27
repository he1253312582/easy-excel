package com.heq.entity.table;

import lombok.Data;

import java.util.Date;

@Data
public class FormulaInfo {
    public FormulaInfo() {
    }

    public FormulaInfo(String name, String formulaCode, Long formulaScenesId) {
        this.name = name;
        this.formulaCode = formulaCode;
        this.formulaScenesId = formulaScenesId;
    }

    private Long id;
    private Date createdAt = new Date();
    private Long operatorId = 100001L;
    private String createdBy = "王昭";
    private String updatedBy = "王昭";
    private Date updatedAt = new Date();
    private Long updateUserId = 100001L;
    private String formulaType = "SENTENCE";
    private String isSynchronize = "NO";
    private String matchRange = "SINGLE_DIALOG";
    private String matchType = "SHOW_ANY";
    private String name;
    private String selected = "NO";
    private Long formulaScenesId;
    private String formulaCode;

}
