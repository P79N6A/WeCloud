package com.bat.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.DeprecationHandler;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 6.2.2 的引入transportClient 引入
 */
@Slf4j
public class ElasticSearch6_2Test {

    private TransportClient client;
    private IndexResponse response;

    @Before
    public void init()throws Exception {
        Settings esSetting = Settings.builder()
                .put("cluster.name","es6-trunk")
                .put("client.transport.sniff",true)
                .build();
//        版本冲出只能引入一个jar
        client = new PreBuiltTransportClient(esSetting)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("10.155.20.50"), 9300));

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


    @Test
    public void getIndex(){
//        QueryStringQueryBuilder matchQueryBuilder = QueryBuilders.queryStringQuery("");
//
//        QueryBuilder queryBuilder = new BoolQueryBuilder();
//        GetResponse getResponse = client.prepareGet("index_t","type_t","4eExtGkB98oJuFh7NEQ7").get();

        SearchRequestBuilder builder = client.prepareSearch("megacorp").setQuery(QueryBuilders.matchAllQuery()).setFetchSource("first_name",null);
        SearchResponse response = builder.get();
        SearchHits hits = response.getHits();
        System.out.println(JSONObject.toJSONString(response));
    }

    @After
    public void after(){
        client.close();
    }



}
