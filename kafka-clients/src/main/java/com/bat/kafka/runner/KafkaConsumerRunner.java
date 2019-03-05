package com.bat.kafka.runner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;

public class KafkaConsumerRunner implements Runnable {

    private KafkaConsumer consumer;

    public KafkaConsumerRunner(KafkaConsumer consumer){
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofSeconds(1));
            for(ConsumerRecord<String,String> record:records){
                System.out.printf("thread:%s,topic:%s,offset:%s,value:%s\n",Thread.currentThread().getName(),record.topic(),record.offset(),record.value());
            }
        }
    }
}
