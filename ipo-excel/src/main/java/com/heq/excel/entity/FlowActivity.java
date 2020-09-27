package com.heq.excel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 工作流活动记录
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlowActivity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "flow_activity_id", type = IdType.AUTO)
    private Integer flowActivityId;

    private Integer flowDefineId =100;
    private Integer flowInstanceId;
    private Integer flowCurrentNodeId;
    private Integer flowOperType;
    private Date flowOperTime;
    private Integer flowOperId;
    private String flowOperPropose;

    private Date nodeDeadlineTime;
    private Date nodeShouldStartTime;

    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 修改人
     */
    private Long updateBy;



}
