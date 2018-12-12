package com.bat.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 想测试下 能不能用ControllerAdvice 来拦截所有的请求
 * 在执行requestMapping的时候do something
 * 貌似是不可以的 需要结合	ExceptionHandler	InitBinder	ModelAttribute
 * 这三个注解一起使用，单独在里面写的方法是没有用处的
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-04 13:06
 **/
@ControllerAdvice
public class LoginAdvice {

	public void login(){
		System.out.println("-------LoginAdvice login--------");
	}
}
