package com.bat.kafka.factory;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.ArrayList;
import java.util.Properties;

public class KafkaConsumerFactory {

    public KafkaConsumer getConsumer(Integer i){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"kafka-1.neibu.koolearn.com:10193,kafka-2.neibu.koolearn.com:10193,kafka-3.neibu.koolearn.com:10193");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,1000);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());

        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"id-"+i);
        KafkaConsumer consumer = new KafkaConsumer(properties);

        consumer.subscribe(new ArrayList<String>(){{add("test-topic");}});
        return consumer;
    }
}
