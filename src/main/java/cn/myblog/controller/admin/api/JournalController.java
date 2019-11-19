package cn.myblog.controller.admin.api;

import cn.myblog.model.dto.JournalDTO;
import cn.myblog.model.entity.Journal;
import cn.myblog.model.param.JournalParam;
import cn.myblog.model.param.JournalQuery;
import cn.myblog.service.JournalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/api/admin/journals")
public class JournalController {
    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public Page<Journal> pageBy(@PageableDefault(sort = "createTime", direction = DESC) Pageable pageable,
                                @RequestBody @Valid JournalQuery journalQuery) {
        return journalService.pageBy(journalQuery, pageable);
    }

    @PostMapping
    public JournalDTO createBy(@RequestBody @Valid JournalParam journalParam) {
        return journalService.saveBy(journalParam);
    }

    @PutMapping("{id:\\d+}")
    public JournalDTO updateBy(@PathVariable Integer id,
                               @RequestBody @Valid JournalParam journalParam) {
        return journalService.updateBy(id, journalParam);
    }

    @DeleteMapping("{id:\\d+}")
    public JournalDTO deleteBy(@PathVariable Integer id) {
        return journalService.deleteBy(id);
    }
}
