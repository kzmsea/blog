package com.kzm.provider;

import com.kzm.entity.Blog;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

public class BlogSqlProvider {

    public String listBlog(Blog blog){
        return new SQL(){{
            SELECT("*");
            FROM("blog");
            if(blog.getTitle()!=null&&blog.getTitle()!=""){
                WHERE("title like concat('%',#{title},'%')");
            }
            if(blog.getTypeId()!=null&&blog.getTypeId()!=0){
                WHERE("typeid = #{typeId}");
            }
            if(blog.getTagId()!=null&&blog.getTagId()!=""){
                WHERE("tagid like concat(#{tagId},',%') or tagid like concat('%,',#{tagId}) or tagid like concat('%,',#{tagId},',%')");
            }
            if(blog.isRecommend()){
                WHERE("recommend = true");
            }
        }}.toString();
    }

}
