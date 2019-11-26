package cn.myblog.model.param;

import cn.myblog.model.dto.base.InputConverter;
import cn.myblog.model.entity.Category;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class CategoryParam implements InputConverter<Category> {

    @NotBlank(message = "分类名称不能为空")
    @Max(value = 255, message = "名称不能超过 {max} ")
    private String name;
}
