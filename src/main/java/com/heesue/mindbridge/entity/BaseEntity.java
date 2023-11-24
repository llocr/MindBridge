package com.heesue.mindbridge.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Transient
    private Long previousCount; // count 필드의 이전 값을 추적

    private Long count;

    @Enumerated(value = EnumType.STRING)
    private BoardStatus status;

    @PostLoad
    private void onLoad() {
        this.previousCount = count; // 엔티티가 로드될 때 현재 count 값을 저장
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        if(!Objects.equals(this.count, this.previousCount)) {
            // count 값이 변경되었으므로 lastModifiedDate를 업데이트하지 않습니다.
            // 이 로직은 count 값이 변경되었을 때만 호출됩니다.
        } else {
            // count 값 외의 다른 변경이 있을 때 lastModifiedDate를 업데이트합니다.
            this.lastModifiedDate = LocalDateTime.now();
        }
    }
}
