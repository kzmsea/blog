package com.kzm.dao;

import com.github.pagehelper.PageInfo;
import com.kzm.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TypeRepository {

    @Insert("insert into type(name) values (#{name})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int saveType(Type type);

    @Select("select * from type where id=#{id}")
    Type getType(Long id);

    @Results({
            @Result(property = "blogs",column = "typeid",many = @Many(select = "com.kzm.dao.BlogRepository.listBlogByTypeId"))
    })
    @Select("SELECT *,id AS typeid FROM TYPE")
    List<Type> listType();

    @Update("update type set name=#{name} where id=#{id}")
    int updateType(Type type);

    @Delete("delete from type where id=#{id}")
    int removeType(Long id);
}
