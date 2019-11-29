package cn.myblog.controller.admin;

import cn.myblog.service.CategoryService;
import cn.myblog.service.JournalService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminContentController {

    private final CategoryService categoryService;

    private final JournalService journalService;

    public AdminContentController(CategoryService categoryService, JournalService journalService) {
        this.categoryService = categoryService;
        this.journalService = journalService;
    }

    @GetMapping
    public String admin(Model model, HttpSession session) {
//        Object token = session.getAttribute("token");
//        if (token == null) {
//            return "redirect:/admin/login";
//        }
        Sort sort = Sort.by("createTime").ascending();
        model.addAttribute("categories", categoryService.top10By(sort));
        return "admin/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        Sort sort = Sort.by("createTime").ascending();
        model.addAttribute("categories", categoryService.top10By(sort));
        return "admin/edit";
    }

    @GetMapping("/edit/{id:\\d+}")
    public String edit(@PathVariable Integer id,
                       Model model) {
        Sort sort = Sort.by("createTime").ascending();
        model.addAttribute("categories", categoryService.top10By(sort));
        model.addAttribute("journal", journalService.fetchBy(id));
        return "admin/edit";
    }

    @GetMapping("/category")
    public String category() {
        return "admin/category";
    }
}
