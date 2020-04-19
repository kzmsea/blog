package com.kzm.web;

import com.kzm.entity.Comment;
import com.kzm.entity.User;
import com.kzm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Value("${comment-avatar}")
    private String avatar;

    @GetMapping("/{blogId}")
    public String listComments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listComment(blogId));
        return "blog :: commentList";
    }

    @PostMapping
    public String post(Comment comment, HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlogId();
    }

}
