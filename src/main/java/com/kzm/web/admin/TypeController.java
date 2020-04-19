package com.kzm.web.admin;

import com.kzm.entity.Type;
import com.kzm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(Integer pageNum,Model model){
        if(pageNum==null||pageNum<=0){
            pageNum=1;
        }
        model.addAttribute("page",typeService.listType(pageNum,5));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        try {
            typeService.saveType(type);
            attributes.addFlashAttribute("mes","保存成功");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("mes","保存失败");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/remove")
    public String remove(@PathVariable Long id,RedirectAttributes attributes){
        try{
            typeService.removeType(id);
            attributes.addFlashAttribute("mes","删除成功");
        }catch (Exception e){
            e.printStackTrace();;
            attributes.addFlashAttribute("mes","删除成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String editPost(Type type, RedirectAttributes attributes){
        try {
            typeService.updateType(type);
            attributes.addFlashAttribute("mes","更新成功");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("mes","更新失败");
        }
        return "redirect:/admin/types";
    }

}
