package com.bat.other;

/**
 * @Author: caoke
 * @Date: 2019/8/7 15:53
 * @Version 1.0
 */
public class RegexTest {
    public static void main(String[] args) {
        /**
         * key  以大小写字母下划线开头，中间可以包含数字下划线，不能以下划线结尾
         * value
         */
        String pattern = "([A-Za-z\\_][A-Za-z0-9]+[\\_]*[A-Za-z0-9]+=[A-Za-z\\_]+([\\.]?[A-Za-z0-9\\_]+)?[\\,]?)+";

        System.out.println("\"data=data\".matches(pattern));"+"data=data".matches(pattern));
        System.out.println("\"_data=data\".matches(pattern));"+"_data=data".matches(pattern));
        System.out.println("\"_data_=data\".matches(pattern));"+"_data_=data".matches(pattern));
        System.out.println("\"_data_=data\".matches(pattern));"+"_data_=data".matches(pattern));
        System.out.println("\"_dat0a_=data\".matches(pattern));"+"_dat0a_=data".matches(pattern));
        System.out.println("\"0dat0a_=data\".matches(pattern));"+"0dat0a_=data".matches(pattern));
        System.out.println("\"A0dat0a_=data\".matches(pattern));"+"A0dat0a_=data".matches(pattern));
        System.out.println("\"_0=data\".matches(pattern));"+"_09=data=da".matches(pattern));








    }
}