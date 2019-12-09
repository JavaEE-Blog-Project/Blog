package cn.myblog.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Lazyzzz
 * @Date 2019/12/8 19:09
 */
@Data
@Entity
@Table(name = "comments")
@EqualsAndHashCode(callSuper = false, exclude = {"journal", "parentComment", "replyComments"})
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nickname", columnDefinition = "varchar(255) not null")
    private String nickname;

    @Column(name = "content", columnDefinition = "varchar(1027) not null")
    private String content;

    @Column(name = "ip_address", columnDefinition = "varchar(127) default''")
    private String ipAddress;

    @ManyToOne
    @JsonManagedReference
    private Journal journal;

    @JsonIgnore
    @ManyToOne
    private Comment parentComment;

    @JsonIgnore
    @OneToMany(mappedBy = "parentComment", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private Set<Comment> replyComments = new HashSet<>();

    @Override
    protected void prePersist() {
        super.prePersist();

        id = null;

        if (ipAddress == null) {
            ipAddress = "";
        }
    }
}
