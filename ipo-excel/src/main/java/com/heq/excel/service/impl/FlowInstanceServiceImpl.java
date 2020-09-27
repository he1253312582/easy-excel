package com.heq.excel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heq.excel.entity.FlowInstance;
import com.heq.excel.entity.IpoProduct;
import com.heq.excel.entity.IpoStockCalendar;
import com.heq.excel.mapper.FlowInstanceMapper;
import com.heq.excel.service.FlowInstanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 流程实例 服务实现类
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Service
public class FlowInstanceServiceImpl  extends ServiceImpl<FlowInstanceMapper, FlowInstance> implements FlowInstanceService {

    @Resource
    private FlowInstanceMapper instanceMapper;

    @Resource
    private IpoStockCalendarServiceImpl stockCalendarService;

    private static final Integer CURRENT_NODE_ID = 60;
    private static final String TENANT_ID = "3204ea72201c49c785e97c7b20939367";


    public void batchInsertFlowInstance(List<FlowInstance> instanceList) {
        instanceMapper.batchInsertFlowInstance(instanceList);
    }

    public List<FlowInstance> instanceComp(List<IpoProduct> productList, IpoStockCalendar stock) {
        Map<String, Date> currentNodeDateMap = stockCalendarService.getStockNodeDate(stock, CURRENT_NODE_ID);
        List<FlowInstance> instanceList = new ArrayList<FlowInstance>();
        for (IpoProduct product : productList) {
            FlowInstance instance = new FlowInstance();
            instanceList.add(instance);
            instance.setTenantId(TENANT_ID);
            instance.setNodeShouldStartTime(currentNodeDateMap.get("nodeShouldStartTime"));
            instance.setNodeDeadlineTime(currentNodeDateMap.get("nodeDeadlineTime"));
            instance.setProductId(product.getProductId());
            instance.setStockCalendarId(stock.getStockCalendarId());
        }
        instanceMapper.batchInsertFlowInstance(instanceList);
//        batchInsertFlowInstance(instanceList);
        return instanceList;
    }
}
