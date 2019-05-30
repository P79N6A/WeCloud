package com.bat.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: caoke
 * @Date: 2019/5/18 15:31
 * @Version 1.0
 */
public class ElasticSyncTest4 {
    private static String url = "jdbc:mysql://10.155.10.133:4200/db_eeo_data";
    private static String username = "jiagou_release";
    private static String password = "release123";
    public static void main(String[] args)throws Exception {

        long startTime = System.currentTimeMillis();
        int limit = 1000;
        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();
        RestTemplate restTemplate = new RestTemplate();

            for (int loop = 0; loop < 450; loop++) {
                int s = loop * limit;
                int e = limit;
                ResultSet resultSet = statement.executeQuery("select * from pe_eeo_room_record order by id limit " + s + "," + e);
                int col = resultSet.getMetaData().getColumnCount();
                List<Map> mapList = Lists.newArrayList();
                while (resultSet.next()) {
                    Map map = Maps.newHashMap();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    for (int i = 1; i <= col; i++) {
                        if(resultSet.getObject(i) instanceof Date){
                            map.put(resultSet.getMetaData().getColumnName(i), dateFormat.format((Date)resultSet.getObject(i)));
                        }else {
                            map.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                        }
                    }
                    mapList.add(map);
                }
                if (mapList.size() < 1){
                    continue;
                }
//                System.out.println(JSONObject.toJSONString(mapList));
//                Thread thread = new Thread(()->{
                    // { "index" : { "_index" : "zhouls", "_type" : "type1", "_id" : "1" } }
                    StringBuilder sb = new StringBuilder();
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
//                });
//                thread.start();
            }
        connection.close();
        long endTime = System.currentTimeMillis();
        System.out.println("cost:"+(endTime - startTime));
    }
}
