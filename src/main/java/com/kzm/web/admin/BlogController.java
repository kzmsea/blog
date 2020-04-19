package com.kzm.web.admin;

import com.kzm.entity.Blog;
import com.kzm.entity.User;
import com.kzm.service.BlogService;
import com.kzm.service.TagService;
import com.kzm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public String blogs(Model model, Blog blog,Integer pageNum){
        if(pageNum==null||pageNum<1){
            pageNum=1;
        }
        model.addAttribute("page",blogService.listBlog(pageNum,5,blog))
                .addAttribute("types",typeService.listType());
        return "admin/blogs";
    }

    @PostMapping("/search")
    public String search(Model model, Blog blog,Integer pageNum){
        if(pageNum==null||pageNum<1){
            pageNum=1;
        }
        model.addAttribute("page",blogService.listBlog(pageNum,5,blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping(value = {"/{id}/input","/input"})
    public String input(@PathVariable(required = false) Long id,Model model){
        if(id!=null){
            model.addAttribute("blog",blogService.getBlog(id)).addAttribute("types",typeService.listType()).addAttribute("tags",tagService.listTag());
        }else{
            model.addAttribute("blog",new Blog()).addAttribute("types",typeService.listType()).addAttribute("tags",tagService.listTag());
        }
        return "admin/blogs-input";
    }

    @PostMapping
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUserId(((User)session.getAttribute("user")).getId());
        if(blog.getId()==null||blog.getId()==0){
            try{
                blogService.saveBlog(blog);
                attributes.addFlashAttribute("mes","添加成功");
            }catch(Exception e){
                attributes.addFlashAttribute("mes","添加失败");
                e.printStackTrace();
            }
        }else{
            try{
                blog.setGxsj(new Date());
                blogService.updateBlog(blog);
                attributes.addFlashAttribute("mes","修改成功");
            }catch(Exception e){
                attributes.addFlashAttribute("mes","修改失败");
                e.printStackTrace();
            }
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable(required = false)Long id,RedirectAttributes attributes){
        try{
            blogService.removeBlog(id);
            attributes.addFlashAttribute("mes","删除成功");
        }catch (Exception e){
            attributes.addFlashAttribute("mes","删除失败");
            e.printStackTrace();
        }
        return "redirect:/admin/blogs";
    }
}
