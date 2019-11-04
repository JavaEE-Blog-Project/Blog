package cn.myblog.model.param;

import cn.myblog.model.enums.JournalType;
import lombok.Data;

@Data
public class JournalQuery {
    /**
     * Keyword
     */
    private String keyword;

    private JournalType type;
}
