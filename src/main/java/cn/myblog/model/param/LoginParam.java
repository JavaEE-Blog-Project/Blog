package cn.myblog.model.param;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ToString
public class LoginParam {

    @NotBlank(message = "用户名或邮箱不能为空")
    @Size(max = 255, message = "用户或邮箱不能超过 {max}")
    private String username;

    @NotBlank(message = "登录密码不能为空")
    @Size(max = 100, message = "密码长度不能超过 {max}")
    private String password;
}
