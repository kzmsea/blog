package com.kzm.web.admin;

import com.kzm.entity.Tag;
import com.kzm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/tags")
public class TagController{

    @Autowired
    private TagService tagService;

    @GetMapping
    public String tags(ModelMap modelMap, Integer pageNum){
        if(pageNum==null||pageNum<1){
            pageNum=1;
        }
        modelMap.addAttribute("page",tagService.listTag(pageNum,5));
        return "admin/tags";
    }

    @GetMapping(value={"/input","/{id}/input"})
    public String input(ModelMap modelMap, @PathVariable(required = false) Long id){
        if(id!=null){
            modelMap.addAttribute("tag",tagService.getTag(id));
        }else{
            modelMap.addAttribute("tag",new Tag());
        }
        return "admin/tags-input";
    }

    @PostMapping
    public String post(Tag tag, RedirectAttributes attributes){
        if(tag.getId()!=null){
            try{
                tagService.updateTag(tag);
                attributes.addFlashAttribute("mes","更新成功");
            }catch(Exception e){
                e.printStackTrace();
                attributes.addFlashAttribute("mes","更新失败");
            }
        }else{
            try{
                tagService.saveTag(tag);
                attributes.addFlashAttribute("mes","保存成功");
            }catch(Exception e){
                e.printStackTrace();
                attributes.addFlashAttribute("mes","保存失败");
            }
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable Long id,RedirectAttributes attributes){
        try{
            tagService.removeTag(id);
            attributes.addFlashAttribute("mes","删除成功");
        }catch(Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("mes","删除成功");
        }
        return "redirect:/admin/tags";
    }

}
