package com.heq.excel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heq.excel.entity.FlowActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工作流活动记录 Mapper 接口
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */


@Mapper
public interface FlowActivityMapper extends BaseMapper<FlowActivity> {

    void batchInsertFlowActivity(@Param("activityList") List<FlowActivity> activityList);

}
