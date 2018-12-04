package com.bat.dao.cas;

import com.bat.domain.cas.WebUserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WebUserLoginDao {

    @Select("select * from web_user_login where id = #{id}")
	WebUserLogin findById(@Param("id") Long id);
}
