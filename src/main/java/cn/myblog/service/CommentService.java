package cn.myblog.service;

import cn.myblog.model.entity.Comment;
import cn.myblog.model.param.CommentParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

/**
 * @Author Lazyzzz
 * @Date 2019/12/8 22:29
 */
public interface CommentService {

    /**
     * Fetch a comment by blog id
     *
     * @param id       blog id
     * @param pageable pageable
     * @return
     */
    @NonNull
    public Page<Comment> pageBy(@NonNull Pageable pageable);

    /**
     * Save a comment by comment parameter
     *
     * @param commentParam comment parameter
     * @return Comment
     */
    @NonNull
    public Comment saveBy(@NonNull CommentParam commentParam);

    /**
     * Delete a comment by id
     *
     * @param id id of the comment
     * @return comment
     */
    @NonNull
    public Comment deleteBy(@NonNull Integer id);
}
