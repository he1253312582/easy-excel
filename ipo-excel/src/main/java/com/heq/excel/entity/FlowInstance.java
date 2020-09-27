package com.heq.excel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.org.glassfish.gmbal.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 流程实例
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlowInstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "flow_instance_id", type = IdType.AUTO)
    private Integer flowInstanceId;
    private Integer flowDefineId = 100;
    private Integer flowCurrentNodeId = 60;
    private Integer flowNextNodeId = 70;
    @Description("-1 终止  1进行中 2结束")
    private Integer flowInstanceStatus = 1;
    private Date flowStartTime = new Date();
    private Integer flowStartUserId = 1;
    private Date flowEndTime;
    private Integer flowEndUserId;
    private Long createBy = 1L;
    private Long updateBy;

    private String tenantId;
    private Integer stockCalendarId;
    private Integer productId;
    private Date nodeShouldStartTime;
    private Date nodeDeadlineTime;


}
