package cn.myblog.model.dto;

import cn.myblog.model.dto.base.OutputConverter;
import cn.myblog.model.entity.Journal;
import cn.myblog.model.enums.JournalType;
import lombok.Data;

import java.util.Date;

@Data
public class JournalDTO implements OutputConverter<JournalDTO, Journal> {

    private Integer id;

    private String title;

    private String content;

    private String image;

    private Long visits;

    private Date createTime;

    private JournalType type;
}
