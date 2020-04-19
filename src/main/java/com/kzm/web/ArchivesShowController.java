package com.kzm.web;

import com.kzm.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archivesShow(Model model){
        model.addAttribute("blogCount",blogService.blogCount())
                .addAttribute("archiveMap",blogService.archiveBlog());
        return "archives";
    }

}
