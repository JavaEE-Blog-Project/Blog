package cn.myblog.controller.content;

import cn.myblog.model.entity.Comment;
import cn.myblog.model.entity.Journal;
import cn.myblog.service.JournalService;
import cn.myblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Lazyzzz
 */
@Controller
@RequestMapping("/journals/{id:\\d+}")
public class JournalContentController {

    private final JournalService journalService;

    private final UserService userService;

    public JournalContentController(JournalService journalService, UserService userService) {
        this.journalService = journalService;
        this.userService = userService;
    }

    @GetMapping
    public String journal(Model model,
                          @PathVariable("id") Integer id) {
        Journal journal = journalService.fetchBy(id);
        Set<Comment> comments = journal.getComments().stream()
                .filter(comment -> comment.getParentComment() == null)
                .collect(Collectors.toSet());
        model.addAttribute("journal", journal);
        model.addAttribute("comments", comments);
        model.addAttribute("user", userService.getCurrentUser());
        journalService.incrVisitsBy(id);
        return "journal";
    }
}
