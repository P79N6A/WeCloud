package com.bat.proxy;

import org.springframework.core.MethodIntrospector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-19 14:15
 **/
public class MyInvocationHandler implements InvocationHandler {

	public Object target ;

	public MyInvocationHandler(Object target){
		this.target =target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		if(method.getName().startsWith("hello")){
			System.out.println("----------------InvocationHandler-----------------");
		}
		return method.invoke(target,args);
	}

	public static void main(String[] args) {
//		MyInvocationHandler invocationHandler = new MyInvocationHandler(new HelloServiceImpl());
//		HelloService hello = (HelloService)Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(),
//				new Class[]{HelloService.class},invocationHandler);
//		hello.hello();
//		hello.hello("caoke");

//		HelloServiceImpl impl = new HelloServiceImpl();
//		HelloService hello2 = (HelloService) Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(),
//				new Class[]{HelloService.class}, (proxy,method,args2)->{
//					if(method.getName().startsWith("hello")){
//						System.out.println("----------helloinvoke------------");
//					}
//					return method.invoke(impl,args2);
//				});
//		hello2.hello();
//		hello2.hello("23");

		Map<Method, Object>  a = MethodIntrospector.selectMethods(MyInvocationHandler.class, new MethodIntrospector.MetadataLookup(){

			@Override
			public Object inspect(Method method) {
				return null;
			}
		});
	}

}
