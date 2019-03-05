package com.bat.kafka.executor;

import com.bat.kafka.factory.KafkaConsumerFactory;
import com.bat.kafka.runner.KafkaConsumerRunner;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafKaMultipleThreadExe {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        KafkaConsumerFactory factory = new KafkaConsumerFactory();
        for(int i=0;i<3;i++){
            KafkaConsumer consumer = factory.getConsumer(i);
            executorService.submit(new KafkaConsumerRunner(consumer));
        }
    }
}
