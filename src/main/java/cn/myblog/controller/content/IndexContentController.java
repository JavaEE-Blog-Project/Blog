package cn.myblog.controller.content;

import cn.myblog.model.enums.JournalType;
import cn.myblog.service.JournalService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequestMapping("/")
public class IndexContentController {
    private final JournalService journalService;

    public IndexContentController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public String index(@PageableDefault(size = 8, sort = "createTime", direction = DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("journals", journalService.pageBy(JournalType.PUBLIC, pageable));
        return "index";
    }
}
