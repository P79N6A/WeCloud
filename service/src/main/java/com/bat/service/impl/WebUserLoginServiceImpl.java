package com.bat.service.impl;

import com.bat.dao.cas.WebUserLoginDao;
import com.bat.domain.cas.WebUserLogin;
import com.bat.service.WebUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-04 20:35
 **/
@Service
public class WebUserLoginServiceImpl implements WebUserLoginService {

	@Autowired
	private WebUserLoginDao webUserLoginDao;

	@Override
	public WebUserLogin findById(Long id){
		return webUserLoginDao.findById(id);
	}
}
