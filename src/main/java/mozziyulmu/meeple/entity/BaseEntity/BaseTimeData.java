package mozziyulmu.meeple.entity.BaseEntity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseTimeData {
    @CreatedDate
    @Column(updatable = false, insertable = true)
    private LocalDateTime created_time;

    @LastModifiedDate
    private LocalDateTime last_modified_time;
}
