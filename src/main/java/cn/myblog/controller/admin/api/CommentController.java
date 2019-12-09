package cn.myblog.controller.admin.api;

import cn.myblog.model.entity.Comment;
import cn.myblog.model.param.CommentParam;
import cn.myblog.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Lazyzzz
 * @Date 2019/12/8 22:18
 */
@RestController
@RequestMapping("/api/admin/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public Page<Comment> pageBy(@RequestParam("page") Integer page,
                                @RequestParam("limit") Integer limit) {
        Pageable pageable = PageRequest.of(page >= 1 ? page - 1 : page, limit, Sort.by("createTime").descending());
        return commentService.pageBy(pageable);
    }

    @PostMapping
    public Comment saveBy(@RequestBody @Valid CommentParam commentParam) {
        return commentService.saveBy(commentParam);
    }

    @DeleteMapping("{id:\\d+}")
    public Comment deleteBy(@PathVariable Integer id) {
        return commentService.deleteBy(id);
    }
}
