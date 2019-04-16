package com.bat.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2.2.1 transportClient 的引入
 */
public class ElasticSearchTest {

    private TransportClient client;

    @Before
    public void init()throws Exception {
        Settings esSetting = Settings.builder()
                .put("cluster.name","node-trunk")
                .put("client.transport.sniff",true)
                .build();
//        client = TransportClient.builder().settings(esSetting).build()
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.155.20.186"),9300));

        List<DiscoveryNode> dNodes = client.connectedNodes();
        System.out.println(JSONObject.toJSONString(dNodes));
    }

    /**
     * 创建一个索引，添加一条数据
     */
    @Test
    public void addIndex(){
        Map<String, Object> json = new HashMap<>();
        json.put("user","kimchy");
        json.put("postDate",new Date().toString());
        json.put("message","trying out Elasticsearch");
        json.put("attr",JSONObject.parse("[{\"id\":1,\"state\":0,\"createTime\":null,\"klbs\":null,\"alias\":\"地区\",\"klbIds\":\"11,44,2381,2383,2742\",\"treeId\":null,\"category\":\"shiti\",\"updateTime\":null},{\"id\":2,\"state\":0,\"createTime\":null,\"klbs\":null,\"alias\":\"院校\",\"klbIds\":\"11,55,44,2381,2383,2742\",\"treeId\":null,\"category\":\"shiti\",\"updateTime\":null}]"));
        System.out.println(JSONObject.toJSONString(json));
//        IndexResponse response = client.prepareIndex("index_t","type_t","3")
//                .setSource(json)
//                .get();
//        System.out.println(JSONObject.toJSONString(response));
    }

    /**
     * 查询一个索引的值
     */
    @Test
    public void getIndex(){
        GetResponse response = client.prepareGet().setIndex("index_t").setType("type_t").setId("1")
//                .setOperationThreaded(false)
                .get();
        System.out.println(JSONObject.toJSONString(response));
    }

    /**
     * 删除一个查询
     */
    @Test
    public void deleteIndex(){
        DeleteResponse response = client.prepareDelete("index_t","type_t","AWjmKHOLMOBYCrDHF-c_").get();
        System.out.println(JSONObject.toJSONString(response));
    }

    /**
     * DeleteByQueryAction
     * 通过查询条件删除，低版本不支持
     */
    @Test
    public void deleteByQurey(){
//        BulkByScrollResponse response =
//                DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
//                        .filter(QueryBuilders.matchQuery("gender", "male")) //查询条件
//                        .source("persons") //index(索引名)
//                        .get();  //执行
//
//        long deleted = response.getDeleted(); //删除文档的数量
    }

    /**
     * 更新索引值
     * upsert 可以有就更新，没有就插入
     */
    @Test
    public void updateIndex(){
        Map<String, Object> json = new HashMap<>();
        json.put("user","caoke");
        json.put("postDate",new Date().toString());
        json.put("message","this is my update");

        // 1
        UpdateRequest request = new UpdateRequest();
        request.index("index_t");
        request.type("type_t");
        request.id("1");
        request.doc(json);
        UpdateResponse response = client.update(request).actionGet();
        System.out.println(JSONObject.toJSONString(response));

        //2
        response = client.prepareUpdate("index_t","type_t","2").setDoc(json).get();
        System.out.println(JSONObject.toJSONString(response));
    }

    /**
     * 多条数据的get
     */
    @Test
    public void multiIndex(){
        // 1
        MultiGetRequest request = new MultiGetRequest();
        request.add("index_t","type_t","1");
        request.add("index_t","type_t","2");
        MultiGetResponse response = client.multiGet(request).actionGet();
        System.out.println(JSONObject.toJSONString(response));
        // 2
        response = client.prepareMultiGet().add("index_t","type_t","2","3").get();
        System.out.println(JSONObject.toJSONString(response));
    }

    /**
     * 批量操作,执行多个请求语句
     */
    @Test
    public void bulkIndex(){
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        IndexRequest request = new IndexRequest();
        bulkRequest.add(request).add(request);
        BulkResponse responses = bulkRequest.get();
        if (responses.hasFailures()) {
            // process failures by iterating through each bulk response item
            //处理失败
        }
    }


    @Test
    public void time(){
        System.out.println(new Date().getTime());
    }


    @After
    public void after(){
        client.close();
    }
}
