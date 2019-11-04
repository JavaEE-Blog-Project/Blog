package cn.myblog.controller.admin.api;

import cn.myblog.model.dto.JournalDTO;
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
    public Page<JournalDTO> pageBy(@PageableDefault(sort = "createTime",direction = DESC) Pageable pageable,
                                   JournalQuery journalQuery) {
        return null; //TO-DO:
    }

    @PostMapping
    public JournalDTO createBy(@RequestBody @Valid JournalParam journalParam) {
        return null;
    }

    @PutMapping("{id:\\d+}")
    public JournalDTO updateBy(@PathVariable Integer id,
                               @RequestBody @Valid JournalParam journalParam) {
        return null;
    }

    @DeleteMapping("{id:\\d+}")
    public JournalDTO deleteBy(@PathVariable Integer id) {
        return null;
    }
}
