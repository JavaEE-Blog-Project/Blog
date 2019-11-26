package cn.myblog.controller.admin.api;

import cn.myblog.model.dto.JournalDTO;
import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import cn.myblog.model.param.JournalParam;
import cn.myblog.model.param.JournalQuery;
import cn.myblog.service.JournalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/journals")
public class JournalController {
    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    @ApiOperation("get all the journals by page & limit")
    public Page<Journal> pageBy(@RequestParam("page") Integer page,
                                @RequestParam("limit") Integer limit) {
        Pageable pageable = PageRequest.of(page >= 1 ? page - 1 : page, limit);
        return journalService.pageBy(pageable);
    }

    @GetMapping("/search")
    @ApiOperation("get all the journals by journal query")
    public Page<Journal> searchBy(@RequestParam("page") Integer page,
                                  @RequestParam("limit") Integer limit,
                                  @RequestParam("keyword") String keyword,
                                  @RequestParam("type") JournalType type) {
        Pageable pageable = PageRequest.of(page >= 1 ? page - 1 : page, limit);
        JournalQuery journalQuery = new JournalQuery();
        journalQuery.setKeyword(keyword);
        journalQuery.setType(type);
        return journalService.pageBy(journalQuery, pageable);
    }

    @PostMapping
    @ApiOperation("create a journal by journal parameter")
    public JournalDTO createBy(@RequestBody @Valid JournalParam journalParam) {
        return journalService.saveBy(journalParam);
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("update a journal by id and journal parameter")
    public JournalDTO updateBy(@PathVariable Integer id,
                               @RequestBody @Valid JournalParam journalParam) {
        return journalService.updateBy(id, journalParam);
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("delete a journal by id")
    public JournalDTO deleteBy(@PathVariable Integer id) {
        return journalService.deleteBy(id);
    }
}
