package cn.myblog.model.dto;

import cn.myblog.model.dto.base.OutputConverter;
import cn.myblog.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class UserDTO implements OutputConverter<UserDTO, User> {

    private String username;

    private String nickname;

    private String email;

    private String avatar;

    private String description;
}
