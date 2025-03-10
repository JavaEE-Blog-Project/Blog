package cn.myblog.model.entity;

import cn.myblog.model.enums.JournalType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "journals")
@EqualsAndHashCode(callSuper = false, exclude = {"category", "comments"})
@ToString(exclude = {"category", "comments"})
public class Journal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", columnDefinition = "varchar(50) not null")
    private String title;

    @Column(name = "summary", columnDefinition = "varchar(200) not null")
    private String summary;

    @Column(name = "original_content", columnDefinition = "text not null")
    private String originalContent;

    @Column(name = "format_content", columnDefinition = "text not null")
    private String formatContent;

    @Column(name = "image", columnDefinition = "varchar(255) not null")
    private String image;

    @Column(name = "visits", columnDefinition = "bigint default 0")
    private Long visits;

    @Column(name = "type", columnDefinition = "int default 1")
    private JournalType type;

    @ManyToOne
    @JsonManagedReference
    private Category category;

    @JsonBackReference
    @OneToMany(mappedBy = "journal", cascade = {CascadeType.REMOVE})
    private Set<Comment> comments = new HashSet<>();

    @Override
    protected void prePersist() {
        super.prePersist();

        id = null;

        if (visits == null || visits < 0) {
            visits = 0L;
        }

        if (type == null) {
            type = JournalType.PUBLIC;
        }
    }
}
