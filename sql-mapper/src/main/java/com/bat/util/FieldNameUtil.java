package com.bat.util;

import java.io.Serializable;

/**
 * @Author: caoke
 * @Date: 2019/3/5 15:52
 * @Version 1.0
 */
public class FieldNameUtil {

    public static String getName(String originName){
        Boolean _start = false ;
        if(originName.startsWith("_")){
            _start = true;
        }
        String[] ons = originName.split("_");
        StringBuffer sb = new StringBuffer(_start?"_":"");
        for(int i=0;i<ons.length;i++){
            if(i==0) {
                sb.append(ons[i]);
                continue;
            }
            String s = String.valueOf(ons[i].charAt(0)).toUpperCase();
            sb.append(s).append(ons[i].substring(1,ons[i].length()));

        }
        return sb.toString();
    }

}
