package com.bat.service;

import com.bat.domain.we.MiaoShaUser;

public interface MiaoShaUserService {
    MiaoShaUser findById(Long id);
    MiaoShaUser findByName(String name);
}
