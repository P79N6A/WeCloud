package com.bat.other;

/**
 * @Author: caoke
 * @Date: 2019/8/9 18:28
 * @Version 1.0
 */
public class FinalTestT {
    public static void main(String[] args) {
        System.out.println(B.i);
    }

    static class B {
        static {
            System.out.println(123);
        }

        public final static int i = 1;
    }
}

