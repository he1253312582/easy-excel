package com.heq.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created user ： heqiang
 * created date : 2019/10/19 2:16
 * Description : No Description
 * version : 1.0
 */
@RestController
@RequestMapping("/datasource")
public class DruidStatController {

    /**
     * // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，
     * 除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
     * @return
     */
    @GetMapping("/druid/stat")
    public Object druidStat() {
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
