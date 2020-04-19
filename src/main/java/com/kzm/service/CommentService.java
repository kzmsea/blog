package com.kzm.service;

import com.kzm.entity.Comment;
import java.util.List;

public interface CommentService {

    List<Comment> listComment(Long blogId);

    int saveComment(Comment comment);

}
