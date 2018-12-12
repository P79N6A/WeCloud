package com.bat.interceptor;

import com.bat.annotation.AccessCtrl;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-12 17:04
 **/
@Service
public class AccessAnnotationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("-----------AccessAnnotationInterceptor preHandle-------------");
		if(handler instanceof HandlerMethod){
			AccessCtrl ctrl = ((HandlerMethod) handler).getMethodAnnotation(AccessCtrl.class);
			if(ctrl == null) {
				return true;
			}
			boolean flag = ctrl.needLogin();
			System.out.println(flag);
		}
		return super.preHandle(request, response, handler);
	}
}
