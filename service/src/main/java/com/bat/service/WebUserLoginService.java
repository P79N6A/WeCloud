package com.bat.service;

import com.bat.domain.cas.WebUserLogin;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-04 20:33
 **/
public interface WebUserLoginService {
	WebUserLogin findById(Long id);
}
