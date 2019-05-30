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
public class ElasticSyncTest3 {
    private static String url = "jdbc:mysql://10.155.10.133:4200/db_eeo_data";
    private static String username = "jiagou_release";
    private static String password = "release123";
    public static void main(String[] args)throws Exception {

        long startTime = System.currentTimeMillis();
        int limit = 1000;
        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();

            for (int loop = 0; loop < 74; loop++) {
                int s = loop * limit;
                int e = limit;
                ResultSet resultSet = statement.executeQuery("select * from pe_eeo_room_record order by id limit " + s + "," + e);
                int col = resultSet.getMetaData().getColumnCount();
                List<Map> mapList = Lists.newArrayList();
                StringBuilder sb = new StringBuilder();
                while (resultSet.next()) {
                    Map map = Maps.newHashMap();
                    for (int i = 1; i <= col; i++) {
                        map.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                    }
                    mapList.add(map);
                }
//                System.out.println(JSONObject.toJSONString(mapList));
                RestTemplate restTemplate = new RestTemplate();
                // { "index" : { "_index" : "zhouls", "_type" : "type1", "_id" : "1" } }
                for (Map map : mapList) {
                    Map in = Maps.newHashMap();
                    in.put("_index", "db_eeo_room_record");
                    in.put("_type", "pe_eeo_room_record");
                    in.put("_id", map.get("id"));
                    Map out = Maps.newHashMap();
                    out.put("index", in);
                    sb.append(JSONObject.toJSONString(out)).append("\n");
                    sb.append(JSONObject.toJSONString(map)).append("\n");
                }
                String response = restTemplate.postForObject("https://elasticsearch2.release.koolearn.com/_bulk", sb.toString(), String.class);
                System.out.println("response:" + response);

            }
        connection.close();
        long endTime = System.currentTimeMillis();
        System.out.println("cost:"+(endTime - startTime));
    }
}
