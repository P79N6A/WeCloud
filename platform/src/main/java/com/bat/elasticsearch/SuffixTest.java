package com.bat.elasticsearch;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: caoke
 * @Date: 2019/5/23 20:07
 * @Version 1.0
 */
public class SuffixTest {
    public static void main(String[] args) {
        String test = "db_user_live_2303";
        if(test.startsWith("db_user_live_")){
            System.out.println("...");
        }
        if(test.endsWith("\\d+")){
            System.out.println("dddd....");
        }
        if(test.matches("db_user_live_\\d+")){
            System.out.println("match1");
        }
        if("db_user_live_".matches("db_user_live_\\d+")){
            System.out.println("match2");
        }
        if("db_user_live_short".matches("db_user_live_\\d+")){
            System.out.println("match3");
        }
        if("db_user_live_short_234".matches("db_user_live_\\d+")){
            System.out.println("match3");
        }


    }

    public void main2() {
        B b = new B();
        b.setB(1);
        b.setA(2);

    }
    @Getter
    @Setter
    static class A {
        private Integer a;
    }

    @Getter
    @Setter
    static class B extends A{
        private Integer b;
    }
}
