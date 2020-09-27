package com.heq.excel.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heq.excel.entity.FlowInstance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 流程实例 Mapper 接口
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Mapper
public interface FlowInstanceMapper extends BaseMapper<FlowInstance> {

    void batchInsertFlowInstance(@Param("instanceList") List<FlowInstance> instanceList);

}
