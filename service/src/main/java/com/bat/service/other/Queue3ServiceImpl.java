package com.bat.service.other;

import com.bat.thread.QueueCustomerThread;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-07 16:19
 **/
//@Service
public class Queue3ServiceImpl {
	@PostConstruct
	public void dowork(){
		System.out.println("----------队列消费start---------------------");
		new QueueCustomerThread().start();
	}
}
