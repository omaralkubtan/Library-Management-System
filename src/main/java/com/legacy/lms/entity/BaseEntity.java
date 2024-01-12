package com.legacy.lms.entity;

import com.legacy.lms.util.helper.Context;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Setter
    @CreatedDate
    protected LocalDateTime createdAt;

    @Setter
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id")
    protected User createdBy;

    @Setter
    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @Setter
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_user_id")
    protected User updatedBy;

    @Setter
    @Column
    protected LocalDateTime deletedAt;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_by_user_id")
    protected User deletedBy;

    public boolean getIsDeleted() {
        return deletedAt != null;
    }

    public void refresh() {
        updatedBy = Context.getCurrentUser();
        updatedAt = LocalDateTime.now();
    }

    public void delete() {
        deletedBy = Context.getCurrentUser();
        deletedAt = LocalDateTime.now();
    }
}
