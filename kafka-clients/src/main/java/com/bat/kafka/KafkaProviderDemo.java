package com.bat.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProviderDemo {

    public static void main(String[] args)throws Exception {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-1.neibu.koolearn.com:10193,kafka-2.neibu.koolearn.com:10193,kafka-3.neibu.koolearn.com:10193");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for(int i=0;i<10;i++){
            RecordMetadata recordMetadata = producer.send(new ProducerRecord<>("test-topic","my name:"+i)).get();
            System.out.println("recordMetadata:"+recordMetadata);
        }
        producer.close();
    }
}
