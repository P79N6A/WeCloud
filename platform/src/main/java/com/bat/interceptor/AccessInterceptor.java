package com.bat.interceptor;

import com.alibaba.fastjson.JSON;
import com.bat.domain.we.MiaoShaUser;
import com.bat.result.CodeMsg;
import com.bat.result.Result;
import com.bat.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.stream.Stream;

/**
 * 此处没有加任何条件
 * 会对所有的请求进行拦截
 * 使用两种方式
 * 1.循环遍历cookies
 * 2.用lambda流计算返回匹配的value的值，同时使用orElse来处理为空的情况
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-12 16:04
 **/
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("----------AccessInterceptor preHandle----------");
		if(request.getSession().getAttribute("user") != null){
			return true;
		}
		System.out.println(UserUtil.getUser().getId());
		Cookie[] cookies = request.getCookies();
		MiaoShaUser miaoShaUser = null;
//		for(Cookie c:cookies){
//			if("sessionCookie".equals(c.getName())){
//				String sessionCookieValue = c.getValue();
//				// do somethind ...	从缓存中取出user
//				miaoShaUser = new MiaoShaUser();
//				break;
//			}
//		}
//		if(miaoShaUser == null){
//			render(response, CodeMsg.SESSION_ERROR);
//			return false;
//		}else {
//			request.getSession().setAttribute("user",miaoShaUser);
//		}
		// lambda
		String sessionValue = Stream.of(cookies)
				.filter(c-> StringUtils.equals("sessionCookie",c.getName()))
				.findFirst().orElse(new Cookie("nil","")).getValue();
		if(StringUtils.isBlank(sessionValue)){
			render(response, CodeMsg.SESSION_ERROR);
			return false;
		}else{
			// do something get user ...
			miaoShaUser = new MiaoShaUser();
			request.getSession().setAttribute("user",miaoShaUser);
		}
		return super.preHandle(request, response, handler);
	}

	private void render(HttpServletResponse response, CodeMsg cm)throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		String str  = JSON.toJSONString(Result.error(cm));
		out.write(str.getBytes("UTF-8"));
		out.flush();
		out.close();
	}
}
