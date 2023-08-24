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
public class CounselorBoard extends BaseEntity{

    @Id @GeneratedValue
    private Long counselorBoardNo;

    @OneToOne(mappedBy = "boardNo")
    private Counselor counselor;

    private String title;

    private String body;

    private String counselingField;

    private int count;

    @Enumerated(value = EnumType.STRING)
    private BoardStatus status;
}
