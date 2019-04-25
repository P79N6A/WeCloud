package com.bat;

import com.bat.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-11-23 18:51
 **/
public class ApplicationStart {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		context.getBean(User.class);
		User user = (User) context.getBean("user");
		System.out.println(user.getId()+":"+user.getName()+":"+user.getDescription());

	}
}
