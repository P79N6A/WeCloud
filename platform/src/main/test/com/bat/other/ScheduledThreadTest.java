package com.bat.other;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
//        a();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        for(int i=0;i<5;i++){
            Task task = new Task();
            task.setName("my-"+i);
            executorService.scheduleWithFixedDelay(task,1,2,TimeUnit.SECONDS);
        }
        Thread.sleep(30000);
        executorService.shutdown();
    }

    static class Task extends Thread {
        @Override
        public void run() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(getName()+ " run " +dateFormat.format(new Date(System.currentTimeMillis())));
            try {
                if(getName().equals("my-0") || getName().equals("my-1") || getName().equals("my-2"))
                    Thread.sleep(11*1000);

            }catch (Exception e){

            }
        }
    }
    private static void a() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Future result = executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + "start....");
                    int num = new Random().nextInt(100);//生成随机数
//                    if (num < 20)
//                        throw new RuntimeException("less than 20");
                    System.out.println(Thread.currentThread().getName() + " : " + num+":"+System.currentTimeMillis());
                    Thread.sleep(4000);
                    System.out.println(Thread.currentThread().getName() + " : " + "end....");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 1,1, SECONDS);
        Thread.sleep(3000);
        boolean a = result.cancel(false);
        System.out.println("a:"+a);
        executorService.shutdown();
    }
}
