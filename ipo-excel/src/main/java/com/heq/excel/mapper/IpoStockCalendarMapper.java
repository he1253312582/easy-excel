package com.heq.excel.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heq.excel.entity.IpoStockCalendar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 新股发行日历(总日历) Mapper 接口
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Mapper
public interface IpoStockCalendarMapper extends BaseMapper<IpoStockCalendar> {

    /**
     * 查询今日询价的股票
     *
     * @return
     */
    List<IpoStockCalendar> getInQueryStock();
}
