package com.bat.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSpringConsumer {

    /**
     *
     * @param record
     */
    @KafkaListener(topics = "test-topic")
    public void onTopic(ConsumerRecord record){
        System.out.printf("topic:%s,offset:%s,value:%s\n",record.topic(),record.offset(),record.value());
    }
}
