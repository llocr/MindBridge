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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counselorBoardNo;

    @OneToOne(mappedBy = "boardNo")
    private Counselor counselor;

    private String title;

    private String content;

    private String counselingField;
}
