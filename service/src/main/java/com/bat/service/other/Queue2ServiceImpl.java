package com.bat.service.other;

import com.bat.thread.QueueCustomerThread;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-07 16:13
 **/
@Service
public class Queue2ServiceImpl implements InitializingBean {

	@Autowired
	private ArrayBlockingQueue<Long> abQueue;

	@Override
	public void afterPropertiesSet() {
		System.out.println("----------队列消费start---------------------");
		new QueueCustomerThread(abQueue).start();
	}
}
