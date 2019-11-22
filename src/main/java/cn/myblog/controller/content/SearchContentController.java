package cn.myblog.controller.content;

import cn.myblog.model.enums.JournalType;
import cn.myblog.model.param.JournalQuery;
import cn.myblog.service.JournalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchContentController {
    private final JournalService journalService;

    public SearchContentController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public String search(Model model,
                         @RequestParam("keyword") String keyword) {
        return this.search(model, keyword, 1);
    }

    @GetMapping("page/{page:\\d+}")
    public String search(Model model,
                         @RequestParam("keyword") String keyword,
                         @PathVariable("page") Integer page) {
        Pageable pageable = PageRequest.of(page >= 1 ? page - 1 : page, 5, Sort.by("createTime").descending());
        JournalQuery journalQuery = new JournalQuery();
        journalQuery.setKeyword(keyword);
        journalQuery.setType(JournalType.PUBLIC);
        model.addAttribute("results", journalService.pageBy(journalQuery, pageable));
        return "search";
    }
}
