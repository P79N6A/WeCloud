package com.bat.elasticsearch;

public class ThreadTest {
    public static void main(String[] args)throws Exception{
        Thread t = new Thread(()->{
            while(true){
                try {

                    Thread.sleep(1000);
                    System.out.println("1");
                }catch (Exception e){

                }
            }
        });
        t.start();
        System.out.println("2");
//        System.exit(0);
    }
}
