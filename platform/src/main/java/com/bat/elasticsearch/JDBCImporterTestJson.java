//package com.bat.elasticsearch;
//
//import com.alibaba.fastjson.JSONObject;
//import org.xbib.tools.JDBCImporter;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JDBCImporterTestJson {
//
//    /**
//     * JDBCImporterTest
//     * {
//     *     {
//     *      "type" : "jdbc",
//     *      "jdbc" : {
//     *          "url" : "jdbc:mysql://10.155.10.133:4202/open_falcon",
//     *          "user" : "jiagou_dev",
//     *          "password" : "jiagou_dev",
//     *          "sql" : "select * from action",
//     *          "index" : "myindex",
//     *          "type" : "mytype"
//     *      }
//     *  }
//     * }
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
////        final JDBCImporter importer = new JDBCImporter();
////        InputStream in = System.in;
////        System.out.println(in.toString());
//        Map<String,Object> map1 = new HashMap<>();
//        map1.put("type","jdbc");
//        Map<String,Object> jdbc = new HashMap<>();
//        jdbc.put("url","jdbc:mysql://10.155.10.133:4202/open_falcon");
//        jdbc.put("user","jiagou_dev");
//        jdbc.put("password","jiagou_dev");
//        jdbc.put("sql","select * from action");
//        jdbc.put("index","myindex");
//        jdbc.put("type","mytype");
//        jdbc.put("schedule","0 0/1 * * * ?");
//        map1.put("jdbc",jdbc);
//
//        InputStream in = new ByteArrayInputStream(JSONObject.toJSONString(map1).getBytes());
//        importer.run("args",in);
////        Settings settings = Settings.settingsBuilder()
////                .put("url", "jdbc:mysql://10.155.10.133:4202/open_falcon")
////                .put("user", "jiagou_dev")
////                .put("password", "jiagou_dev")
////                .put("sql", "select * from action")
////                .put("index", "jdbc")
////                .put("type", "jdbc")
////                .build();
////        importer.setSettings(settings);
////        importer.run();
//        Thread.sleep(12000L);
//        importer.shutdown();
//    }
//}
