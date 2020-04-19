package com.kzm.web;

import com.github.pagehelper.PageInfo;
import com.kzm.entity.Blog;
import com.kzm.service.BlogService;
import com.kzm.service.TagService;
import com.kzm.service.TypeService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @RequestMapping("/")
    public String index(Integer pageNum, Model model){
        if(pageNum==null||pageNum<1){
            pageNum=1;
        }
        model.addAttribute("page",blogService.listBlog(pageNum,7,new Blog()));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(6));
        model.addAttribute("recommendBlogs",blogService.listBlogByGxsj(7));
        return "index";
    }

    @PostMapping("/search")
    public String search(Integer pageNum,String query,Model model){
        if(pageNum==null||pageNum<1){
            pageNum=1;
        }
        PageInfo<Blog> page = blogService.listBlogBySearch(pageNum, 99, query);
        model.addAttribute("page",page);
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String detail(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlog(id);
        String content = blog.getContent();
        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        content = renderer.render(document);
        blog.setContent(content);
        model.addAttribute("blog",blog);
        blogService.updateViews(id);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String  newblogs(Model model){
        model.addAttribute("newblogs",blogService.listBlogByGxsj(3));
        return "_fragments :: newblogList";
    }

}
