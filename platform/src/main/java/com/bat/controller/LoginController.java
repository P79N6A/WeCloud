package com.bat.controller;

import com.bat.domain.MiaoShaUser;
import com.bat.result.CodeMsg;
import com.bat.result.Result;
import com.bat.service.MiaoShaUserService;
import com.bat.utils.MD5Util;
import com.bat.utils.UUIDUtil;
import com.bat.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-11-23 18:53
 **/
@Controller
public class LoginController {

    @Autowired
    private MiaoShaUserService miaoShaUserService;

	@RequestMapping("/login")
	@ResponseBody
	public Result<String> login(HttpServletRequest request, HttpServletResponse response,LoginVo loginVo){
		if(loginVo == null || StringUtils.isBlank(loginVo.getName()) || StringUtils.isBlank(loginVo.getPassword())){
			return Result.error(CodeMsg.SERVER_ERROR);
		}
		MiaoShaUser user = miaoShaUserService.findByName(loginVo.getName());
		if(user == null){
			return Result.error(CodeMsg.USERNAME_NOT_FOUND);
		}
		String passFornt = MD5Util.md5(loginVo.getPassword()+ user.getSalt());
		if(!user.getPassword().equals(passFornt)){
			return Result.error(CodeMsg.PASSWORD_IS_WRONG);
		}
		if(loginVo.getAcceptCookie()){
			Cookie cookie = new Cookie("sessionCookie", UUIDUtil.getUuid());
			response.addCookie(cookie);
		}
		return Result.success("登录成功");
	}
}
