package com.heq.excel.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heq.excel.entity.IpoStockCalendar;
import com.heq.excel.mapper.IpoStockCalendarMapper;
import com.heq.excel.service.IpoStockCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 新股发行日历(总日历) 服务实现类
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Slf4j
@Service
public class IpoStockCalendarServiceImpl extends ServiceImpl<IpoStockCalendarMapper, IpoStockCalendar> implements IpoStockCalendarService {

    @Resource
    private IpoStockCalendarMapper ipoStockCalendarMapper;


    /**
     * 获取询价的股票信息
     */
    public List<IpoStockCalendar> queryStockForInquiry() {

        //todo 以下为测试代码，需要删除
        LambdaQueryWrapper<IpoStockCalendar> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(IpoStockCalendar::getStockCalendarId, Arrays.asList("300".split(",")));
        List<IpoStockCalendar> stockList = ipoStockCalendarMapper.selectList(queryWrapper);
        //List<IpoStockCalendar> stockList = ipoStockCalendarMapper.getInQueryStock();
        return stockList;
    }

    /**
     * 根据股票Id和流程节点来读取该股票节点的的开始时间和结束时间
     *
     * @param stockCalendar
     * @param nodeId
     * @return nodeShouldStartTime | nodeDeadlineTime
     */
    public Map<String, Date> getStockNodeDate(IpoStockCalendar stockCalendar, Integer nodeId) {
        Map<String, Date> stockDateMap = new HashMap<>();
        log.info("读取股票中各个节点的截止日期");
        Date nodeShouldStartTime = null;
        Date nodeDeadlineTime = null;
        if (!StringUtils.isEmpty(stockCalendar.getNodeDataJson())) {
            String NodeDataJson = stockCalendar.getNodeDataJson();
            HashMap<String, String> NodeDateMap = JSONArray.parseObject(NodeDataJson, HashMap.class);
            try {
                String shouldDate = NodeDateMap.get(nodeId + "_1");
                String deadlineDate = NodeDateMap.get(nodeId + "_2");
                nodeShouldStartTime = shouldDate == null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(shouldDate);
                nodeDeadlineTime = deadlineDate == null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(deadlineDate);
            } catch (ParseException e) {
                log.info("时间格式转化异常！！！");
                e.printStackTrace();
            }
        }
        stockDateMap.put("nodeShouldStartTime", nodeShouldStartTime);
        stockDateMap.put("nodeDeadlineTime", nodeDeadlineTime);
        return stockDateMap;
    }
}
