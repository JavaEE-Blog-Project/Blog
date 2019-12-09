package cn.myblog.model.param;

import cn.myblog.model.dto.base.InputConverter;
import cn.myblog.model.entity.Comment;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author Lazyzzz
 * @Date 2019/12/8 22:46
 */
@Data
public class CommentParam implements InputConverter<Comment> {

    @NotNull
    private Integer blogId;

    @NotNull
    private Integer parentCommentId;

    @NotBlank(message = "评论昵称不能为空")
    private String nickname;

    @NotBlank(message = "评论邮箱不能为空")
    @Email(message = "评论邮箱格式不正确")
    private String email;

    @NotNull(message = "评论内容不能为空")
    private String content;
}
