package com.bat.other;

import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.*;

/**
 * @Author: caoke
 * @Date: 2019/5/15 17:25
 * @Version 1.0
 */
public class ScheduledThreadTest {
    public static void main(String[] args)throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Future result = executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    int num = new Random().nextInt(100);//生成随机数
                    if (num < 20)
                        throw new RuntimeException("less than 20");
                    System.out.println(Thread.currentThread().getName() + " : " + num+":"+System.currentTimeMillis());
                    Thread.sleep(4000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 1,2, SECONDS);


        System.out.println(result.get());
    }
}
