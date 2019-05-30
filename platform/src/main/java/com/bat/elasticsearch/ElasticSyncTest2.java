package com.bat.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * @Author: caoke
 * @Date: 2019/5/18 15:31
 * @Version 1.0
 */
public class ElasticSyncTest2 {
    private static String url = "jdbc:mysql://10.155.10.38:4501/db_userlive";
    private static String username = "shark";
    private static String password = "shark";
    public static void main(String[] args)throws Exception {
        long startTime = System.currentTimeMillis();
        int limit = 10;
        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();
        int s = 0 * limit;
        int e = limit;
        ResultSet resultSet = statement.executeQuery("select * from pr_user_live_0 limit " + s + "," + e);
        int col = resultSet.getMetaData().getColumnCount();
        List<Map> mapList = Lists.newArrayList();
        while (resultSet.next()) {
            Map map = Maps.newHashMap();
            for (int i = 1; i <= col; i++) {
                map.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
            }
            mapList.add(map);
        }
        System.out.println(JSONObject.toJSONString(mapList));
        connection.close();
        long endTime = System.currentTimeMillis();
        System.out.println("cost:"+(endTime - startTime));
    }
}
