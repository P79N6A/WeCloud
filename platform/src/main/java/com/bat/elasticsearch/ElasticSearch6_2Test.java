package com.bat.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 6.2.2 的引入transportClient 引入
 */
public class ElasticSearch6_2Test {

    private TransportClient client;
    private IndexResponse response;

    @Before
    public void init()throws Exception {
        Settings esSetting = Settings.builder()
                .put("cluster.name","elasticsearch")
                .put("client.transport.sniff",true)
                .build();
//        版本冲出只能引入一个jar
//        client = new PreBuiltTransportClient(esSetting)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        List<DiscoveryNode> dNodes = client.connectedNodes();
        System.out.println(JSONObject.toJSONString(dNodes));
    }

    @Test
    public void addIndex(){
        Map<String, Object> json = new HashMap<>();
        json.put("user","kimchy");
        json.put("postDate",new Date());
        json.put("message","trying out Elasticsearch");

        response = client.prepareIndex("index_t","type_t")
                .setSource(json)
                .get();
        System.out.println(JSONObject.toJSONString(response));
    }

    @After
    public void after(){
        client.close();
    }
}
