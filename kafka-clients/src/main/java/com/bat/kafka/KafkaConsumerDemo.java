package com.bat.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaConsumerDemo {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"kafka-1.neibu.koolearn.com:10193,kafka-2.neibu.koolearn.com:10193,kafka-3.neibu.koolearn.com:10193");
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
//        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,1000);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());

        KafkaConsumer consumer = new KafkaConsumer(properties);
        Map<String, List<PartitionInfo>> map = consumer.listTopics();
        System.out.println(JSONObject.toJSONString(map));

//        consumer.subscribe(new ArrayList<String>(){{add("test-topic");}});

//        while (true){
//            ConsumerRecords<String,String> records = consumer.poll(Duration.ofSeconds(1));
//            for(ConsumerRecord record:records){
//                System.out.printf("topic:%s,offset:%s,value:%s\n",record.topic(),record.offset(),record.value());
//            }
//        }

    }
}
