package cn.myblog.service.impl;

import cn.myblog.exception.BadRequestException;
import cn.myblog.model.entity.Comment;
import cn.myblog.model.param.CommentParam;
import cn.myblog.repository.CommentRepository;
import cn.myblog.service.CommentService;
import cn.myblog.service.JournalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;


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
    public Page<Comment> searchBy(String keyword, Pageable pageable) {
        return commentRepository.findAll(this.buildQuery(keyword), pageable);
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

    @NonNull
    private Specification<Comment> buildQuery(@Nullable String keyword) {
        return (Specification<Comment>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();

            if (keyword != null) {
                String likeCondition = String.format("%%%s%%", StringUtils.strip(keyword));
                predicates.add(criteriaBuilder.like(root.get("content"), likeCondition));
            }

            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }
}
