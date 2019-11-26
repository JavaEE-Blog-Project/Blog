package cn.myblog.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true, exclude = "journalSet")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(255) unique not null")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private Set<Journal> journalSet = new HashSet<>();

    @Override
    protected void prePersist() {
        super.prePersist();

        id = null;
    }

}
