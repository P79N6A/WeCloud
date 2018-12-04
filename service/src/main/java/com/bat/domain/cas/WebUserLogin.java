package com.bat.domain.cas;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-04 20:03
 **/
@Setter
@Getter
@ToString
public class WebUserLogin {
	private Integer id;
	private String userName;
	private String userPassword;
	private String userSource;
	private String userStatus;
}
