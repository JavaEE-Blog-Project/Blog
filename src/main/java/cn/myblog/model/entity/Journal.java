package cn.myblog.model.entity;

import cn.myblog.model.enums.JournalType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "journals")
@EqualsAndHashCode(callSuper = false, exclude = "category")
public class Journal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", columnDefinition = "varchar(50) not null")
    private String title;

    @Column(name = "content", columnDefinition = "varchar(1023) not null")
    private String content;

    @Column(name = "image", columnDefinition = "varchar(255) not null")
    private String image;

    @Column(name = "views", columnDefinition = "bigint default 0")
    private Long views;

    @Column(name = "type", columnDefinition = "int default 1")
    private JournalType type;

    @JsonBackReference
    @ManyToOne
    private Category category;

    @Override
    protected void prePersist() {
        super.prePersist();

        id = null;

        if (views == null || views < 0) {
            views = 0L;
        }

        if (type == null) {
            type = JournalType.PUBLIC;
        }
    }
}
