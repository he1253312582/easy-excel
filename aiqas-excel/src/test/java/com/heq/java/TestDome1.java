package com.heq.java;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Created user ： heqiang
 * created date : 2019/10/20 4:04
 * Description : No Description
 * version : 1.0
 */
public class TestDome1<pvt> {
    @Test
    public void test() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(null);
        set.add(2);
        set.add(null);
        set.add(3);
        set.add(null);
        set.add(4);
        System.out.println(set);
        set.remove(null);
        System.out.println(set);
        set.removeAll(null);
        System.out.println(set);

    }

    @Test
    public void test2() {
        String format = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(format);

    }


}
