package cn.myblog.controller.content;

import cn.myblog.model.enums.JournalType;
import cn.myblog.service.JournalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexContentController {

    private final JournalService journalService;

    public IndexContentController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public String index(Model model) {
        return this.index(model, 0);
    }

    @GetMapping("page/{page:\\d+}")
    public String index(Model model,
                        @PathVariable("page") Integer page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("createTime").descending());
        model.addAttribute("journals", journalService.pageBy(JournalType.PUBLIC, pageable));
        return "index";
    }
}
