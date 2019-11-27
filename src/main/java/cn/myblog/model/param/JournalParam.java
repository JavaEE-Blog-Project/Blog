package cn.myblog.model.param;

import cn.myblog.model.dto.base.InputConverter;
import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class JournalParam implements InputConverter<Journal> {

    @NotBlank(message = "标题不能为空")
    @Size(max = 50, message = "标题长度不能超过{max}")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String originalContent;

    @NotBlank(message = "首图地址不能为空")
    @Size(max = 255, message = "地址长度不能超过{max}")
    private String image;

    @NotNull(message = "分类不能为空")
    private Integer categoryId;

    @NotNull(message = "类型不能为空")
    private JournalType type;
}
