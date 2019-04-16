package com.bat.other;

import com.bat.vo.LoginVo;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: caoke
 * @Date: 2019/3/25 20:10
 * @Version 1.0
 */
public class CounterBaofei {

    @Test
    public void doCount(){
        int benjin = 3000 ;
        int n = 31;
        int bjsum = 0;
        double sum = 0;
        double sum2 = 0;
        int jiaofeiyears = 5;
        int qukuanyears = 10;
        System.out.println(String.format("%4s\t%4s\t%4s\t%6s\t%6s\t%6s\t","年度","存入","本金累计","本息合计","支出","剩余本息"));
        for(int i=1;i<n;i++){
            if(i<=jiaofeiyears) {
                sum = (sum + benjin) * 1.04;
                sum2 = (sum2 + benjin) * 1.04;
                bjsum += benjin;
                System.out.println(String.format("%6d\t%6d\t%6d\t%12f\t%6d\t%12f\t",i,benjin,bjsum,sum,0,sum2));
            }else if(i>jiaofeiyears && i<=qukuanyears){
                sum *= 1.04;
                sum2 *= 1.04;
                System.out.println(String.format("%6d\t%6d\t%6d\t%12f\t%6d\t%12f\t",i,0,bjsum,sum,0,sum2));
            }else{
                sum *= 1.04;
                sum2 = (sum2 - 1200) * 1.04;
                System.out.println(String.format("%6d\t%6d\t%6d\t%12f\t%6d\t%12f\t",i,0,bjsum,sum,1200,sum2));
            }
        }
    }

    @Test
    public void domainNullTest(){
        try {
            LoginVo vo = new LoginVo();
            vo.setName(null);
            vo.getName();



            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MINUTE,20);
            Date lessonStartTime = calendar.getTime();
            System.out.println(lessonStartTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
