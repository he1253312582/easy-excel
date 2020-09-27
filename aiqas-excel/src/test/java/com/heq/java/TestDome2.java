package com.heq.java;

import com.heq.entity.table.TemplateFormula;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.mockito.internal.util.StringUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestDome2 {
    @Test
    public  void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = TemplateFormula.class;
        //获取无参构造器
        Constructor constructor2 = clazz.getConstructor();
        Constructor constructor1 = clazz.getConstructor(Long.class, Long.class);
        T o2 = (T) constructor2.newInstance();
        Object o1 = constructor1.newInstance(100L, 200L);
        System.out.println(o1);
        Method getString = clazz.getMethod("getString", Long.class, Long.class);
        Object aa = (String) getString.invoke(o1, 300L, 400L);
        System.out.println(aa);
    }

    @Test
    public  void test3() {
        System.out.println("CD"+StringUtils.leftPad("2018", 8, "0"));
    }

}
