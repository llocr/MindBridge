package com.heesue.mindbridge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class CounselingRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counselingRequestNo;

    @ManyToOne
    @JoinColumn(name = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "counselorNo")
    private Counselor counselor;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "majorNo")
    private Major major;

    private Integer grade;

    private String phone;

    private String category;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    private String time;

    private String process;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;
}
