package com.bat.other;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: caoke
 * @Date: 2019/7/22 20:19
 * @Version 1.0
 */
public class EsTaskImport {
    public static void main(String[] args)throws Exception {
        List<String> list = new ArrayList<String>();
        FileInputStream inputStream = new FileInputStream("C:\\Users\\caoke\\Desktop\\import.txt");
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader reader1 = new BufferedReader(reader);
        String line = "";
        while ((line = reader1.readLine()) != null) {
            list.add(line);
        }
        System.out.println(list.size());

        TaskBean bean = JSONObject.parseObject(list.get(0),TaskBean.class);
        System.out.println(bean.getTaskName());

        write(list);

    }

    private static void write(List<String> list)throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.155.10.133:4201/es_job","jiagou_ceshi","CeShi*0409.");
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        for(int i=0;i<list.size();i++){
            String sql = getStringSql(list.get(i));
            boolean flag =  statement.execute(sql);
        }

//        for(int i=2;i<502;i++){
//            String sql = getUpdateSql(i);
//            System.out.println(sql);
//            boolean flag =  statement.execute(sql);
//        }

        statement.close();
        connection.commit();
        connection.close();

    }

    private static String getUpdateSql(int i){
        Random random = new Random();
        String sql = "update xxl_job_info set job_cron = '"+random.nextInt(60)+" "+random.nextInt(10)+"/10 * * * ?' where id = " +i;
        return sql;
    }

    private static String getStringSql(String s) {
        Random random = new Random();
        TaskBean bean = JSONObject.parseObject(s,TaskBean.class);
        StringBuilder builder = new StringBuilder();
        builder.append("insert into `xxl_job_info` (" +
                "`job_group`, " +
                "`job_cron`, " +
                "`job_desc`, " +
                "`add_time`, " +
                "`update_time`, " +
                "`author`, " +
                "`alarm_email`, " +
                "`executor_route_strategy`, " +
                "`executor_handler`, " +
                "`executor_param`, " +
                "`executor_block_strategy`, " +
                "`executor_timeout`, " +
                "`executor_fail_retry_count`," +
                "`glue_type`, " +
                "`glue_source`, " +
                "`glue_remark`, " +
                "`glue_updatetime`, " +
                "`child_jobid`, " +
                "`trigger_status`) ");
        builder.append("values(" +
                "'2'," +
                "'"+random.nextInt(60)+" "+random.nextInt(10)+"/10 * * * ?'," +
                "'"+bean.getTaskName()+"'," +
                "'2019-07-22 18:28:22'," +
                "'2019-07-22 19:52:56'," +
                "'caoke'," +
                "'caoke@Koolearn-inc.com'," +
                "'ROUND'," +
                "'elasticSearchJobHandler'," +
                "'"+s+"'" +
                ",'DISCARD_LATER'," +
                "'30'," +
                "'0'," +
                "'BEAN'," +
                "''," +
                "'GLUE代码初始化'," +
                "'2019-07-22 18:28:22'," +
                "''," +
                "'1')");
        return builder.toString();
    }


}
