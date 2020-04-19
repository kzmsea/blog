package com.kzm.dao;

import com.kzm.entity.Blog;
import com.kzm.entity.Type;
import com.kzm.entity.User;
import com.kzm.provider.BlogSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BlogRepository {

    @Results({
            @Result(property = "type",column = "typeId",javaType = Type.class,one = @One(select = "com.kzm.dao.TypeRepository.getType")),
            @Result(property = "user",column = "userId",javaType = User.class,one = @One(select = "com.kzm.dao.UserRepository.getUser"))
    })
    @Select("select * from blog where id=#{id}")
    Blog getBlog(Long id);

    @Results({
            @Result(property = "type",column = "typeId",javaType = Type.class,one = @One(select = "com.kzm.dao.TypeRepository.getType")),
            @Result(property = "user",column = "userId",javaType = User.class,one = @One(select = "com.kzm.dao.UserRepository.getUser"))
    })
    @SelectProvider(type = BlogSqlProvider.class,method = "listBlog")
    List<Blog> listBlog(Blog blog);

    @Insert("INSERT INTO `blog`.`blog` (" +
            "  `title`," +
            "  `content`," +
            "  `firstPicture`," +
            "  `flag`," +
            "  `appreciation`," +
            "  `shareStatement`," +
            "  `commentabled`," +
            "  `published`," +
            "  `recommend`," +
            "  `description`," +
            "  `typeid`," +
            "  `tagid`," +
            "  `userid`" +
            ") " +
            "VALUES" +
            "  (" +
            "    #{title}," +
            "    #{content}," +
            "    #{firstPicture}," +
            "    #{flag}," +
            "    #{appreciation}," +
            "    #{shareStatement}," +
            "    #{commentabled}," +
            "    #{published}," +
            "    #{recommend}," +
            "    #{description}," +
            "    #{typeId}," +
            "    #{tagId}," +
            "    #{userId}" +
            "  )" +
            "")
    int saveBlog(Blog blog);

    @Delete("delete from blog where id=#{id}")
    int removeBlog(Long id);

    @Update("UPDATE " +
            "  `blog`.`blog` " +
            "SET" +
            "  `title` = #{title}," +
            "  `content` = #{content}," +
            "  `firstPicture` = #{firstPicture}," +
            "  `flag` = #{flag}," +
            "  `appreciation` = #{appreciation}," +
            "  `shareStatement` = #{shareStatement}," +
            "  `commentabled` = #{commentabled}," +
            "  `published` = #{published}," +
            "  `recommend` = #{recommend}," +
            "  `description` = #{description}," +
            "  `gxsj` = #{gxsj}," +
            "  `typeid` = #{typeId}," +
            "  `tagid` = #{tagId}," +
            "  `userid` = #{userId} " +
            "WHERE `id` = #{id} ;" +
            "")
    int updateBlog(Blog blog);

    @Select({"select * from blog where tagid like concat(#{id},',%') or tagid like concat('%,',#{id}) or tagid like concat('%,',#{id},',%')"})
    List<Blog> listBlogByTagId(Long id);

    @Select("select * from blog where typeid = #{id}")
    List<Blog> listBlogByTypeId(Long id);

    @Select("select * from blog where recommend is true order by gxsj desc limit 0,#{size}")
    List<Blog> listBlogByGxsj(int size);

    @Results({
            @Result(property = "type",column = "typeId",javaType = Type.class,one = @One(select = "com.kzm.dao.TypeRepository.getType")),
            @Result(property = "user",column = "userId",javaType = User.class,one = @One(select = "com.kzm.dao.UserRepository.getUser"))
    })
    @Select("select * from blog where title like concat('%',#{query},'%') or content like concat('%',#{query},'%')")
    List<Blog> listBlogBySearch(String query);

    @Update("update blog set views=views+1 where id=#{id}")
    int updateViews(Long id);

    @Select("SELECT DATE_FORMAT(djsj,'%Y') TIME FROM blog GROUP BY DATE_FORMAT(djsj,'%Y') ORDER BY TIME DESC")
    List<String> listGroupYear();

    @Select("select * from blog where DATE_FORMAT(djsj,'%Y')=#{year}")
    List<Blog> listBlogByYear(String year);

    @Select("select count(1) from blog")
    int blogCount();

}
