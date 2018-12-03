package com.bat.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-03 15:54
 **/
@Getter
@Setter
@ToString
public class LoginVo {
	private String name;
	private String password;
	private Boolean acceptCookie;
}
