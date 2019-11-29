package cn.myblog.model.param;

import cn.myblog.model.enums.JournalType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JournalQuery {
    /**
     * Keyword
     */
    @NotBlank(message = "查询条件不能为空")
    private String keyword;

    private Integer categoryId;

    private JournalType type;
}
