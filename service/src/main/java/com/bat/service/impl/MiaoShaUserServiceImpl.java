package com.bat.service.impl;

import com.bat.dao.MiaoShaUserDao;
import com.bat.domain.MiaoShaUser;
import com.bat.service.MiaoShaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MiaoShaUserServiceImpl implements MiaoShaUserService {

//    @Autowired
    @Resource(name = "miaoShaUserDao")
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
