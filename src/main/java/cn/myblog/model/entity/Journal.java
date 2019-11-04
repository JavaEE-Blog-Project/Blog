package cn.myblog.model.entity;

import cn.myblog.model.enums.JournalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "journals")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Journal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", columnDefinition = "varchar(1023) not null")
    private String content;

    @Column(name = "likes", columnDefinition = "bigint default 0")
    private Long likes;

    @Column(name = "type", columnDefinition = "int default 1")
    private JournalType type;

    @Override
    protected void prePersist() {
        super.prePersist();

        id = null;

        if (likes == null || likes < 0) {
            likes = 0L;
        }

        if (type == null) {
            type = JournalType.PUBLIC;
        }
    }
}
