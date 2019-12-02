package cn.myblog.controller.content;

import cn.myblog.model.enums.JournalType;
import cn.myblog.service.CategoryService;
import cn.myblog.service.JournalService;
import cn.myblog.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lazyzzz
 */
@Controller
@RequestMapping("/")
public class IndexContentController {

    private final JournalService journalService;

    private final CategoryService categoryService;

    private final UserService userService;

    public IndexContentController(JournalService journalService, CategoryService categoryService, UserService userService) {
        this.journalService = journalService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        return this.index(model, 1);
    }

    @GetMapping("page/{page:\\d+}")
    public String index(Model model,
                        @PathVariable("page") Integer page) {
        Sort sort = Sort.by("createTime").descending();
        Pageable pageable = PageRequest.of(page >= 1 ? page - 1 : page, 5, sort);
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("journals", journalService.pageBy(JournalType.PUBLIC, pageable));
        model.addAttribute("categories", categoryService.top10By(sort));
        return "index";
    }
}
