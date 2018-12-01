package com.bat.service.impl;

import com.bat.dao.MiaoShaUserDao;
import com.bat.domain.MiaoShaUser;
import com.bat.service.MiaoShaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoShaUserServiceImpl implements MiaoShaUserService {

    @Autowired
    private MiaoShaUserDao miaoShaUserDao;

    @Override
    public MiaoShaUser findById(Long id){
        return miaoShaUserDao.findById(id);
    }
}
