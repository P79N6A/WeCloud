package com.bat.kafka;

public class Readme {
    /**
     * kafka-clients来实现对topic的订阅消费
     * 单线程
     * 构造一个配置properties，构造一个KafkaConsumer，订阅一个topic
     * while(true) 一直poll消息,接收到 ConsumerRecord
     * 多线程
     * 构造一个ConsumerFactory用来get到 Consumer ，此时的consumer还没有订阅topic
     * 构造一个ConsumerRunner线程类，接收consumer，topic，来启动线程，在run方法中while(true)
     * 构造一个执行类，用一个线程池
     * 用factory构造多个consumer，做为参数传递给ConsumerRunner提交给线程池
     *
     * 生产者
     * 构造配置，生成一个Producer,send发送消息
     * 用ProducerRecord来封装消息
     *
     */
}
