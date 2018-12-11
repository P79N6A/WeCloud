package com.bat.config.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-10 10:16
 **/
@Configuration
public class QueueConfig {
	@Bean
	public ArrayBlockingQueue<Long> abQueue(){
		return new ArrayBlockingQueue<>(1012);
	}
}
