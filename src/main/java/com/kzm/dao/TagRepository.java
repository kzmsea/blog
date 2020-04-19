package com.kzm.dao;

import com.kzm.entity.Blog;
import com.kzm.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TagRepository {

    @Insert("insert into tag(name) values (#{name})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int saveTag(Tag tag);

    @Delete("delete from tag where id=#{id}")
    int removeTag(Long id);

    @Update("update tag set name=#{name} where id=#{id}")
    int updateTag(Tag tag);

    @Results({
            @Result(property = "blogs",column = "tagid",many = @Many(select = "com.kzm.dao.BlogRepository.listBlogByTagId"))
    })
    @Select("select *,id AS tagid from tag")
    List<Tag> listTag();

    @Select("select * from tag where id=#{id}")
    Tag getTag(Long id);


}
