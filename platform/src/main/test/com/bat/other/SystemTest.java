package com.bat.other;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: caoke
 * @Date: 2019/7/23 20:38
 * @Version 1.0
 */
public class SystemTest {
    public static void main(String[] args)throws Exception {
//        String a = System.getProperty("a.b.c");
//        System.out.println("a:"+a);
//        Map<String, List<Integer>> ml = Maps.newHashMap();
//        ml.computeIfAbsent("1",s -> new ArrayList<Integer>(){{add(Integer.valueOf(2));}}).add(3);
//        System.out.println(JSONObject.toJSONString(ml));

//        A a = new A();
//        System.out.println(a);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
//        Date date = dateFormat.parse(dateFormat.format(new Date()));
//        System.out.println(date);
//        Long _7day = 7 * 24 * 60 * 60 * 1000L;
//        System.out.println(new Date(date.getTime()+_7day));
        A a = new A();
        a.a = 23L;

        A b = new A();
        System.out.println(a.a+b.a);


    }

    static class A{
        Long a ;
    }
}
