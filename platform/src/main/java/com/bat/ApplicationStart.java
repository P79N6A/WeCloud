package com.bat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-11-23 18:51
 **/
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true,exposeProxy=true)
public class ApplicationStart {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}
}
