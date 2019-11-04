package cn.myblog.controller.content;

import cn.myblog.model.dto.JournalDTO;
import cn.myblog.model.enums.JournalType;
import cn.myblog.service.JournalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequestMapping("/journals")
public class ContentJournalController {

    private final JournalService journalService;

    public ContentJournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public String journals(Model model) {
        return journals(model,1,Sort.by(DESC,"createTime"));
    }

    @GetMapping("page/{page}")
    public String journals(Model model,
                           @PathVariable("page") Integer page,
                           @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        Pageable pageable = PageRequest.of(page >= 1 ? page-1 : page, 5,sort);

        List<JournalDTO> journals = journalService.pageBy(JournalType.PUBLIC, pageable);

        model.addAttribute("journals",journals);

        return null;
    }
}
