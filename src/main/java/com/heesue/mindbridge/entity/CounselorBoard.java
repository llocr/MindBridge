package com.heesue.mindbridge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    //제목
    private String title;

    //내용
    private String content;

    //사용하는 상담기법
    private String counselingField;
}
