package cn.myblog.model.entity;

import cn.myblog.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", columnDefinition = "varchar(50) not null")
    private String username;

    @Column(name = "nickname", columnDefinition = "varchar(255) not null")
    private String nickname;

    @Column(name = "password", columnDefinition = "varchar(255) not null")
    private String password;

    @Column(name = "email", columnDefinition = "varchar(127) default ''")
    private String email;

    @Column(name = "avatar", columnDefinition = "varchar(1023) default ''")
    private String avatar;

    @Column(name = "description", columnDefinition = "varchar(1023) default ''")
    private String description;

    @Column(name = "expire_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime;

    @Override
    public void prePersist() {
        super.prePersist();

        id = null;

        if (email == null) {
            email = "";
        }

        if (avatar == null) {
            avatar = "";
        }

        if (description == null) {
            description = "";
        }

        if (expireTime == null) {
            expireTime = DateUtils.now();
        }
    }
}
