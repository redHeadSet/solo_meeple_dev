package mozziyulmu.meeple.entity.BaseEntity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseUserData extends BaseTimeData{
    @CreatedBy
    @Column(updatable = false, insertable = true)
    private String created_user;

    @LastModifiedBy
    private String last_modified_user;
}
