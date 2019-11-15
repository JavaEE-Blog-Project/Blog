package cn.myblog.controller.content;

import cn.myblog.service.JournalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/journals/{id:\\d+}")
public class JournalContentController {

    private final JournalService journalService;

    public JournalContentController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public String journal(Model model,
                          @PathVariable("id") Integer id) {
        model.addAttribute("journal", journalService.fetchBy(id));
        return "journal";
    }
}
