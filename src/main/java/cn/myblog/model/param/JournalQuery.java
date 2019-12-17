package cn.myblog.model.param;

import cn.myblog.model.enums.JournalType;
import lombok.Data;

@Data
public class JournalQuery {

    private String keyword;

    private Integer categoryId;

    private JournalType type;
}
