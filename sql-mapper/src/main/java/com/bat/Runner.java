package com.bat;

import com.bat.service.GenerateFile;

/**
 * @Author: caoke
 * @Date: 2019/3/5 17:39
 * @Version 1.0
 */
public class Runner {
    public static void main(String[] args)throws Exception {
        String pkg = "com.bat.target";
        String tableName = "action";
        String className = "Action";
        String path = "sql-mapper/src/main/java/";

        GenerateFile.out(pkg,tableName,className,path);
    }
}
