package com.bat.service.other;

import com.bat.queue.QueueUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 服务启动的时候，就任务加载进去
 * 用ApplicationRunner不会阻塞主线程的加载
 * 实现 InitializingBean 接口 或 用PostConstruct注解都会阻塞主线程的运行
 * 可新建一个线程来完成任务的加载
 *
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-07 11:31
 **/
//@Service
public class QueueServiceImpl implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception{
		System.out.println("---------------------start-------------------");
		while (true){
			while(QueueUtil.abQueue.size()>0){
				Long time = QueueUtil.abQueue.take();
				System.out.println(time);
				Thread.sleep(1000);
			}
		}
	}

}
