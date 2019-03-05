package com.bat.kafka;

public class Readme {
    /**
     * spring-kafka
     * 利用spring-boot启动，加载kafka的配置文件
     * 引入KafKaTemplate
     * 通过Controller每次调用出发Kafka的写入
     *
     * 监听方面
     * 利用spring注解@KafkaListener(topics = "test-topic")
     * 在指定方法上面指定监听kafka,指定需要监听的topic
     *
     * 监听者需要分组，一个组下面可以有多个监听者（进程/线程）但是只有一个会收到消息
     * 不同的组可以同时收到一条消息
     *
     */
}
