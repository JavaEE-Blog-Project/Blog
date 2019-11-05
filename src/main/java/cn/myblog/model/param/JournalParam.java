package cn.myblog.model.param;

import cn.myblog.model.dto.base.InputConverter;
import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class JournalParam implements InputConverter<Journal> {

    @NotBlank(message = "博客内容不能为空")
    @Size(max = 1023, message = "博客长度不能超过{max}")
    private String content;

    private JournalType journalType = JournalType.PUBLIC;
}
