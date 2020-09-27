package com.heq.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * Created user ： heqiang
 * created date : 2019/10/19 2:29
 * Description : No Description
 * version : 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTest {

    @Autowired
    DataSource dataSource;
    @Test
    public void test(){
        System.out.println(dataSource);

    }

}
