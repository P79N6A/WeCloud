package com.bat.service.other;

import com.bat.thread.QueueCustomerThread;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-07 16:13
 **/
//@Service
public class Queue2ServiceImpl implements InitializingBean {
	@Override
	public void afterPropertiesSet() {
		System.out.println("----------队列消费start---------------------");
		new QueueCustomerThread().start();
	}
}
