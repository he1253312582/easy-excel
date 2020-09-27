package com.heq.entity.table;

import lombok.Data;
import java.util.Date;

@Data
public class ScenesInfo {

    public ScenesInfo(String name, String remark,String scenesCode) {
        this.name = name;
        this.remark = remark;
        this.scenesCode = scenesCode;
    }

    private long id;
    private Date createdAt = new Date();
    private long operatorId = 100001;
    private String createdBy = "王昭";
    private String updatedBy = "王昭";
    private Date updatedAt = new Date();
    private long updateUserId = 100001;
    private String isSynchronize = "NO";
    private String name;
    private String remark;
    private String scenesCode;
    private Double criticalValues=0.5;


}
