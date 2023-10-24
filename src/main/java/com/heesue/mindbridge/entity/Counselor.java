package com.heesue.mindbridge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Counselor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counselorNo;

    @OneToOne
    @JoinColumn(name = "id", unique = true)
    private Member counselorId;

    private Boolean clubCheck;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    private LocalDateTime appliedDateTime;

    @OneToMany(mappedBy = "counselor", fetch = FetchType.LAZY)
    private List<CounselingRequest> counselingRequestList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "counselorBoardNo")
    private CounselorBoard boardNo;
}
