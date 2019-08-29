package com.bat.other.threadsafe;

/**
 * @Author: caoke
 * @Date: 2019/8/13 17:33
 * @Version 1.0
 */
public class StaticTest implements Runnable {

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
        i=2;
        System.out.println("["+Thread.currentThread().getName()+"] 当前静态变量i="+i);
        i=5;
        System.out.println("["+Thread.currentThread().getName()+"] 当前静态变量i="+i*2);
    }

    public static void main(String[] args) {
        StaticTest test = new StaticTest();
        for(int i=0;i<1000;i++){
            new Thread(test,"线程"+i).start();
        }
        /**
         * [线程245] 当前静态变量i=2
         * [线程245] 当前静态变量i=4
         * [线程246] 当前静态变量i=2
         * [线程172] 当前静态变量i=10
         * [线程177] 当前静态变量i=5
         * [线程177] 当前静态变量i=10
         * [线程130] 当前静态变量i=2
         * [线程130] 当前静态变量i=10
         * [线程178] 当前静态变量i=5
         * [线程178] 当前静态变量i=10
         * [线程169] 当前静态变量i=2
         * [线程169] 当前静态变量i=10
         * [线程179] 当前静态变量i=5
         * [线程179] 当前静态变量i=10
         * [线程171] 当前静态变量i=2
         * [线程171] 当前静态变量i=10
         * [线程180] 当前静态变量i=5
         */
    }
}

