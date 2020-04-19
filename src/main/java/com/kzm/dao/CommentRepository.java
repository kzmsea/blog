package com.kzm.dao;

import com.kzm.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentRepository {

    /*@Select("select * from comment where blogid=#{blogId}")
    List<Comment> listComment(Long blogId);*/

    @Results({
            @Result(property = "replyComments",column = "rid",many = @Many(select = "com.kzm.dao.CommentRepository.listReplyComments"))
    })
    @Select("select *,id as rid from comment where blogid=#{blogId} and parentCommentId = 0")
    List<Comment> listComment(Long blogId);

    @Results({
            @Result(property = "replyComments",column = "rid",many = @Many(select = "com.kzm.dao.CommentRepository.listReplyComments")),
            @Result(property = "parentComment",column = "pid",one = @One(select = "com.kzm.dao.CommentRepository.getParentComment"))
    })
    @Select("select *,id as rid,parentCommentId as pid from comment where parentCommentId=#{id}")
    List<Comment> listReplyComments(Long id);

    @Select("select * from comment where id=#{id}")
    Comment getParentComment(Long id);

    @Insert("INSERT INTO `blog`.`comment` (\n" +
            "  `nickname`,\n" +
            "  `email`,\n" +
            "  `content`,\n" +
            "  `avatar`,\n" +
            "  `parentCommentid`,\n" +
            "  `blogid`,\n" +
            "  `adminComment`\n" +
            ") \n" +
            "VALUES\n" +
            "  (\n" +
            "    #{nickname},\n" +
            "    #{email},\n" +
            "    #{content},\n" +
            "    #{avatar},\n" +
            "    #{parentCommentId},\n" +
            "    #{blogId},\n" +
            "    #{adminComment}\n" +
            "  ) ")
    int saveComment(Comment comment);

}
