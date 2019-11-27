package cn.myblog.controller.admin;

import cn.myblog.service.CategoryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminContentController {

    private final CategoryService categoryService;

    public AdminContentController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String admin(Model model) {
        Sort sort = Sort.by("createTime").ascending();
        model.addAttribute("categories", categoryService.top10By(sort));
        return "admin/dashboard";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        Sort sort = Sort.by("createTime").ascending();
        model.addAttribute("categories", categoryService.top10By(sort));
        return "admin/edit";
    }
}
