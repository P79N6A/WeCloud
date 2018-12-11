package com.bat.thread;

import com.bat.queue.QueueUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-07 16:15
 **/
public class QueueCustomerThread extends Thread {

	private ArrayBlockingQueue<Long> abQueue;

	public QueueCustomerThread(ArrayBlockingQueue<Long> abQueue){
		this.abQueue = abQueue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				while (abQueue.size() > 0) {
//					Long time = QueueUtil.abQueue.take();
					Long time = abQueue.take();
					System.out.println(time);
					Thread.sleep(1000);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
