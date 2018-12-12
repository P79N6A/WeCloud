package com.bat.config;

import com.bat.interceptor.AccessAnnotationInterceptor;
import com.bat.interceptor.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 用本类来添加对springweb环境的其他配置
 * 比如添加参数解析器，拦截器等
 * 同时可以设置该拦截器的拦截规则和启动拦截的顺序
 * 不加order，默认按照代码加入的顺序
 *
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-12 15:45
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private AccessInterceptor accessInterceptor;
	@Autowired
	private AccessAnnotationInterceptor accessAnnotationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessAnnotationInterceptor);
		registry.addInterceptor(accessInterceptor);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

	}
}
