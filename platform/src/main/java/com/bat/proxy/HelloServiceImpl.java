package com.bat.proxy;

import org.springframework.aop.config.AopConfigUtils;
import org.springframework.aop.config.AopNamespaceUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;

/**
 * @program: ICloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-10-16 15:40
 **/
public class HelloServiceImpl implements HelloService{
	@Override
	public String hello() {
		System.out.println("---------------------hello----------------");
//		((HelloServiceImpl)AopContext.currentProxy()).hello("23");
		this.hello("23");
		return "Hello";
	}

	@Override
	public String hello(String name) {
		System.out.println("---------------------hello,"+name+"----------------");
		return "Hello," + name;
	}
}
