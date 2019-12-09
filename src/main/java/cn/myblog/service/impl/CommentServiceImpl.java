package cn.myblog.service.impl;

import cn.myblog.exception.BadRequestException;
import cn.myblog.model.entity.Comment;
import cn.myblog.model.param.CommentParam;
import cn.myblog.repository.CommentRepository;
import cn.myblog.service.CommentService;
import cn.myblog.service.JournalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * @Author Lazyzzz
 * @Date 2019/12/8 22:49
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final JournalService journalService;

    public CommentServiceImpl(CommentRepository commentRepository,
                              JournalService journalService) {
        this.commentRepository = commentRepository;
        this.journalService = journalService;
    }

    @Override
    public Page<Comment> pageBy(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment saveBy(CommentParam param) {
        Comment comment = param.convertTo(new Comment());
        comment.setJournal(journalService.fetchBy(param.getBlogId()));

        Integer parentCommentId = param.getParentCommentId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }

        commentRepository.save(comment);

        return comment;
    }

    @Override
    public Comment deleteBy(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BadRequestException("该评论不存在"));
        commentRepository.delete(comment);
        return comment;
    }
}
