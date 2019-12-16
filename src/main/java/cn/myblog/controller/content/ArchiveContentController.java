package cn.myblog.controller.content;

import cn.myblog.service.JournalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Lazyzzz
 * @Date 2019/12/16 20:53
 */
@Controller
@RequestMapping("/archive")
public class ArchiveContentController {

    private final JournalService journalService;

    public ArchiveContentController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public String archive(Model model) {
        model.addAttribute("items", journalService.groupByYear());
        return "archive";
    }
}
