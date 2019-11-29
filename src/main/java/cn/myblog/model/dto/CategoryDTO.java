package cn.myblog.model.dto;

import cn.myblog.model.dto.base.OutputConverter;
import cn.myblog.model.entity.Category;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO implements OutputConverter<CategoryDTO, Category> {
    private String name;

    private Integer count;

    private Date createTime;

    private Date updateTime;
}
