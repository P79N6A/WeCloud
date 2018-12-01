package com.bat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-11-23 18:51
 **/
@SpringBootApplication
public class ApplicationStart {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}
}
