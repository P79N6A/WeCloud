package com.bat.other.threadsafe;

/**
 * @Author: caoke
 * @Date: 2019/8/13 17:33
 * @Version 1.0
 */
public class NormalTest implements Runnable {

    // 此处定义一个静态变量
    private static int i;

    /**
     * 在线程运行的时候，如果正常的情况下应该输出，2,10
     * 如果证明线程不安全，即其他线程修改了别的线程正常运行的值
     * 会出现A线程把i修改为5，即将执行i*2=10的时候，其他线程B把i修改为2，使结果输出为4
     * A线程把i赋值为2，即将执行输出i=2的时候，线程B把i修改为5，使结果输出为5
     * 无论输出4,5都是线程不安全的体现
     */
    @Override
    public void run() {
        add();
    }

    public void add() {
        i=2;
        System.out.println("["+Thread.currentThread().getName()+"] 当前变量i="+i);
        i=5;
        System.out.println("["+Thread.currentThread().getName()+"] 当前变量i="+i*2);
    }

    public static void main(String[] args) {
        NormalTest t = new NormalTest();
        for(int i=0;i<1000;i++){
            new Thread(t,"线程"+i).start();
        }
        /**
         * [线程161] 当前变量i=2
         * [线程161] 当前变量i=4
         * [线程251] 当前变量i=2
         * [线程794] 当前变量i=10
         * [线程818] 当前变量i=5
         * [线程818] 当前变量i=10
         */
    }
}

