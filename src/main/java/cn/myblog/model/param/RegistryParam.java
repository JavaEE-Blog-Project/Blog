package cn.myblog.model.param;

import cn.myblog.model.dto.base.InputConverter;
import cn.myblog.model.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistryParam implements InputConverter<User> {

    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名长度不能超过 {max}")
    private String username;

    @NotBlank(message = "昵称不能为空")
    @Size(max = 255, message = "昵称长度不能超过 {max}")
    private String nickname;

    @NotBlank(message = "密码不能为空")
    @Size(max = 255, message = "密码长度不能超过 {max}")
    private String password;

    private String email;

    private String avatar;

    private String description;
}
