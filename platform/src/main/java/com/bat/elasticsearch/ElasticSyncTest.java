package com.bat.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
public class ElasticSyncTest {
    private static String url = "jdbc:mysql://10.155.10.38:4501/db_userlive";
    private static String username = "shark";
    private static String password = "shark";
    public static void main(String[] args)throws Exception {
        Connection connection = null;
        try {
            long startTime = System.currentTimeMillis();
            int limit = 1000;
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            for (int size = 12; size < 100; size++) {
                ResultSet rs = statement.executeQuery("select count(*) from pr_user_live_" + size);
                rs.next();
                long count = (long) rs.getObject(1);
                long maxloop = count / limit + 1;
                for (int loop = 0; loop < maxloop; loop++) {
                    int s = loop * limit;
                    int e = limit;
                    ResultSet resultSet = statement.executeQuery("select * from pr_user_live_" + size + " limit " + s + "," + e);
                    int col = resultSet.getMetaData().getColumnCount();
                    List<Map> mapList = Lists.newArrayList();
                    StringBuilder sb = new StringBuilder();
                    while (resultSet.next()) {
                        Map map = Maps.newHashMap();
                        for (int i = 1; i <= col; i++) {
                            if (resultSet.getMetaData().getColumnName(i).equals("member_rights")
                                    || resultSet.getMetaData().getColumnName(i).equals("elective")
                                    || resultSet.getMetaData().getColumnName(i).equals("is_delete")
                            ) {
                                map.put(resultSet.getMetaData().getColumnName(i), (Boolean) resultSet.getObject(i) == false ? 0 : 1);
                            } else {
                                map.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                            }
                        }
                        mapList.add(map);
                    }
    //                System.out.println(JSONObject.toJSONString(mapList));
                    RestTemplate restTemplate = new RestTemplate();
                    // { "index" : { "_index" : "zhouls", "_type" : "type1", "_id" : "1" } }
                    for (Map map : mapList) {
                        Map in = Maps.newHashMap();
                        in.put("_index", "db_user_live");
                        in.put("_type", "user_live");
                        in.put("_id", "pr_user_live_" + size + "_" + map.get("id"));
                        Map out = Maps.newHashMap();
                        out.put("index", in);
                        sb.append(JSONObject.toJSONString(out)).append("\n");
                        sb.append(JSONObject.toJSONString(map)).append("\n");
                    }
                    HttpHeaders headers = new HttpHeaders();
                    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                    headers.setContentType(type);
                    headers.add("Accept", MediaType.APPLICATION_JSON.toString());


                    HttpEntity<String> formEntity = new HttpEntity<>(sb.toString(), headers);
                    String response = restTemplate.postForObject("http://10.155.20.51:9200/_bulk", formEntity, String.class);
//                    String response = restTemplate.postForObject("http://10.155.20.51:9200/_bulk", sb.toString(), String.class);
                    System.out.println("response:" + response);

                }
            }
            connection.close();
            long endTime = System.currentTimeMillis();
            System.out.println("cost:" + (endTime - startTime));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                    connection.close();
            }
        }
    }
}
