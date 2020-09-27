package com.heq.excel.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heq.excel.entity.ExcelProduct;
import com.heq.excel.entity.IpoProduct;
import com.heq.excel.mapper.IpoProductMapper;
import com.heq.excel.service.IpoProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 * @author HeQiang
 * @since 2020-09-25
 */
@Slf4j
@Service
public class IpoProductServiceImpl extends ServiceImpl<IpoProductMapper, IpoProduct> implements IpoProductService {

    @Resource
    private IpoProductMapper productMapper;

    /**
     * 根据产品名称查询产品信息
     *
     * @param productNameSet
     * @return
     */
    public List<IpoProduct> queryProductByNameList(Set<String> productNameSet) {
        LambdaQueryWrapper<IpoProduct> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(IpoProduct::getProductName, productNameSet);
        queryWrapper.eq(IpoProduct::getProductStatus, 1);
        List<IpoProduct> productList = productMapper.selectList(queryWrapper);
        return productList;
    }

    /**
     * 更新产品信息
     *
     * @param productList
     * @param productMap
     */
    public void updateProduct(List<IpoProduct> productList, Map<String, ExcelProduct> productMap) {
        for (IpoProduct ipoProduct : productList) {
            ExcelProduct excelProduct = productMap.get(ipoProduct.getProductName());
            ipoProduct.setAllowStockType(excelProduct.getAllowStockType());
        }
    }


    /**
     * 筛选出数据库中不存在的产品信息
     *
     * @param productList
     * @param excelProductMap
     */
    public List<ExcelProduct> filterNotDBProduct(List<IpoProduct> productList, Map<String, ExcelProduct> excelProductMap) {
        List<ExcelProduct> excelProductList = new ArrayList<>();
        Set<String> excelProductNameSet = excelProductMap.keySet();
        Set<String> dbProductNameSet = productList.stream().map(IpoProduct::getProductName).collect(Collectors.toSet());
        for (String excelProductName : excelProductNameSet) {
            if (!dbProductNameSet.contains(excelProductName)) {
                excelProductList.add(excelProductMap.get(excelProductName));
            }
        }
        return excelProductList;
    }
}
