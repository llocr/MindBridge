package com.heesue.mindbridge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class CounselorBoard {

    @Id
    @OneToOne(mappedBy = "counselorNo")
    private Counselor counselor;

    private String title;

    private String body;

    private String counselingField;

    private int count;

    private Date createdDate;

    private Date modifiedDate;

    @Enumerated(value = EnumType.STRING)
    private BoardStatus status;
}
