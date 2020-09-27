package com.heq.excel.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heq.excel.entity.IpoProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品表 Mapper 接口
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Mapper
public interface IpoProductMapper extends BaseMapper<IpoProduct> {

    int updateBatchById(@Param("productList") List<IpoProduct> productList);

}
