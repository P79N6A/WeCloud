package com.bat.service.impl;

import com.bat.dao.we.MiaoShaUserDao;
import com.bat.domain.we.MiaoShaUser;
import com.bat.service.MiaoShaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoShaUserServiceImpl implements MiaoShaUserService {

    @Autowired
    public MiaoShaUserDao miaoShaUserDao;

    @Override
    public MiaoShaUser findById(Long id){

        return miaoShaUserDao.findById(id);
    }

    @Override
    public MiaoShaUser findByName(String name){
        return miaoShaUserDao.findByName(name);
    }
}
