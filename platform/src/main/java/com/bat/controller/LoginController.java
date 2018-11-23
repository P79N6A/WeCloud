package com.bat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-11-23 18:53
 **/
@RestController
public class LoginController {

	@RequestMapping("/sayHello")
	public String login(){
		return "hello caoke";
	}
}
