package com.kzm.web;

import com.github.pagehelper.PageInfo;
import com.kzm.entity.Blog;
import com.kzm.entity.Tag;
import com.kzm.service.BlogService;
import com.kzm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tagShow(@PathVariable Long id, Integer pageNum, Model model){
        if(pageNum==null||pageNum<1){
            pageNum=1;
        }
        List<Tag> tags = tagService.listTag();
        if(id==-1&&tags.size()>0){
            id=tags.get(0).getId();
        }
        Blog blog=new Blog();
        blog.setTagId(id.toString());
        model.addAttribute("tags",tags)
                .addAttribute("page",blogService.listBlog(pageNum, 5, blog))
                .addAttribute("activeTagId",id);
        return "tags";
    }

}
