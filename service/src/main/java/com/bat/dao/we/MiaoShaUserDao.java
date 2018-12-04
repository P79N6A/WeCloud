package com.bat.dao.we;

import com.bat.domain.we.MiaoShaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MiaoShaUserDao {

    @Select("select * from miao_sha_user where id = #{id}")
    MiaoShaUser findById(@Param("id")Long id);

    @Select("select * from miao_sha_user where name = #{name}")
	MiaoShaUser findByName(@Param("name")String name);
}
