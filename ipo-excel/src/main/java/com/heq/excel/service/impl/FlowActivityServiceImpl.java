package com.heq.excel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heq.excel.entity.FlowActivity;
import com.heq.excel.entity.FlowInstance;
import com.heq.excel.mapper.FlowActivityMapper;
import com.heq.excel.service.FlowActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 工作流活动记录 服务实现类
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Slf4j
@Service
public class FlowActivityServiceImpl extends ServiceImpl<FlowActivityMapper, FlowActivity> implements FlowActivityService {

    @Resource
    private FlowActivityMapper flowActivityMapper;

    public void batchInsertFlowInstance(List<FlowInstance> instanceList) {
        List<FlowActivity> activityList = new ArrayList<FlowActivity>();
        for (FlowInstance instance : instanceList) {
            FlowActivity activity = new FlowActivity();
            activityList.add(activity);
            activity.setFlowCurrentNodeId(instance.getFlowCurrentNodeId());
            activity.setFlowDefineId(instance.getFlowDefineId());
            activity.setFlowInstanceId(instance.getFlowInstanceId());
            activity.setNodeShouldStartTime(instance.getNodeShouldStartTime());
            activity.setNodeDeadlineTime(instance.getNodeDeadlineTime());
            activity.setFlowOperTime(new Date());
            activity.setFlowOperType(1);
            activity.setFlowOperId(1);
            activity.setCreateBy(1L);
        }
        flowActivityMapper.batchInsertFlowActivity(activityList);
    }


}
