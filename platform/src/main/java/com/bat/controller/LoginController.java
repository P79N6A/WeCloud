package com.bat.controller;

import com.bat.domain.MiaoShaUser;
import com.bat.service.MiaoShaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-11-23 18:53
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MiaoShaUserService miaoShaUserService;

	@RequestMapping("/sayHello")
	public String login(Model model){
        MiaoShaUser user = miaoShaUserService.findById(1L);
	    model.addAttribute("name",user.getName());
		return "hello";
	}
}
