package com.kzm.web;

import com.kzm.entity.Blog;
import com.kzm.entity.Type;
import com.kzm.service.BlogService;
import com.kzm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{id}")
    public String typeShow(@PathVariable Long id,Integer pageNum,Model model){
        if(pageNum==null||pageNum<1){
            pageNum=1;
        }
        List<Type> types = typeService.listType();
        if(id==-1&&types.size()>0){
            id=types.get(0).getId();
        }
        Blog blog=new Blog();
        blog.setTypeId(id);
        model.addAttribute("types",types)
                .addAttribute("page",blogService.listBlog(pageNum,5,blog))
                .addAttribute("activeTypeId",id);
        return "types";
    }

}
