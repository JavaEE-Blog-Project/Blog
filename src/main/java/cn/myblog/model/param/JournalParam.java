package cn.myblog.model.param;

import cn.myblog.model.enums.JournalType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class JournalParam {

    @NotBlank(message = "博客内容不能为空")
    @Size(max = 1023, message = "博客长度不能超过{max}")
    private String content;

    private JournalType journalType = JournalType.PUBLIC;
}
