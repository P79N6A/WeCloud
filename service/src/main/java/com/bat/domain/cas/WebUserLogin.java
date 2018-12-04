package com.bat.domain.cas;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-04 20:03
 **/
@Setter
@Getter
public class WebUserLogin {
	private Integer id;
	private String user_name;
	private String userPassword;
	private String userSource;
	private String userStatus;
}
