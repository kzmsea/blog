package com.kzm.service;

import com.kzm.dao.CommentRepository;
import com.kzm.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    private List<Comment> secondComments=new ArrayList<>();

    @Override
    public List<Comment> listComment(Long blogId) {
        List<Comment> comments = commentRepository.listComment(blogId);
        /*List<Comment> firstComments=new ArrayList<>();
        for(Comment comment:comments){
            if(comment.getParentCommentId()==0){
                firstComments.add(comment);
            }
        }*/
        eachComment(comments);
        return comments;
    }

    public void eachComment(List<Comment> firstComments){
        for(Comment comment:firstComments){
            List<Comment> replyComments = comment.getReplyComments();
            if(replyComments.size()>0){
                recursively(replyComments);
            }
            comment.setReplyComments(secondComments);
            secondComments=new ArrayList<>();
        }
    }

    public void recursively(List<Comment> comments){
        for (Comment comment:comments){
            secondComments.add(comment);
            if(comment.getReplyComments().size()>0){
                recursively(comment.getReplyComments());
            }
        }
    }

    @Override
    public int saveComment(Comment comment) {
        if(comment.getParentCommentId()==-1){
            comment.setParentCommentId(0L);
        }
        return commentRepository.saveComment(comment);
    }
}
