package com.bat.dao;

import com.bat.domain.MiaoShaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MiaoShaUserDao {

    @Select("select * from miao_sha_user where id = #{id}")
    MiaoShaUser findById(@Param("id")Long id);
}
