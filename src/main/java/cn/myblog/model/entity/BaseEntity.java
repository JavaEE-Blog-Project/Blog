package cn.myblog.model.entity;

import cn.myblog.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@MappedSuperclass
@EqualsAndHashCode
public class BaseEntity {

    /**
     * Create Time
     */
    @Column(name = "create_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * Update Time
     */
    @Column(name = "update_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * Delete Flag
     */
    @Column(name = "deleted", columnDefinition = "TINYINT default 0")
    private Boolean deleted = false;

    @PrePersist
    protected void prePersist() {
        deleted = false;
        Date now = DateUtils.now();
        if (createTime == null) {
            createTime = now;
        }
        if (updateTime == null) {
            updateTime = now;
        }
    }

    @PreUpdate
    protected void preUpdate() {
        updateTime = DateUtils.now();
    }

    @PreRemove
    protected void preRemove() {
        updateTime = DateUtils.now();
    }
}
