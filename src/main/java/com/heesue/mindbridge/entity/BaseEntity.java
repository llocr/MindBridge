package com.heesue.mindbridge.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Long count;
    @Enumerated(value = EnumType.STRING)
    private BoardStatus status;
}
