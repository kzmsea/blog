package com.kzm.dao;

import com.kzm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface UserRepository {

    @Select("select * from user where username=#{username} and password=#{password}")
    User checkUser(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where id=#{id}")
    User getUser(Long id);

}
