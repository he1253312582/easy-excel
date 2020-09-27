package com.heq.entity.table;

import lombok.Data;
import java.util.Date;

@Data
public class TemplateInfo {

    public TemplateInfo() {
    }

    public TemplateInfo(String content, long scenesId) {
        this.content = content;
        this.scenesId = scenesId;
    }

    private long id;
    private Date createdAt = new Date();
    private long operatorId = 100001;
    private String createdBy = "王昭";
    private String updatedBy = "王昭";
    private Date updatedAt = new Date();
    private long updateUserId = 100001;
    private String content;
    private long scenesId;


}
