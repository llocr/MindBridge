//package com.heesue.mindbridge.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table
//public class Counselor {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long counselorNo;
//
//    private String id;
//
//    private String pwd;
//
//    private String name;
//
//    private Long studentNo;
//
//    private String email;
//
//    private Date birth;
//
//    private String description;
//
//    private LocalDateTime enrollDate;
//
//    @OneToMany(mappedBy = "counselor", fetch = FetchType.LAZY)
//    private List<CounselingRequest> counselingRequestList = new ArrayList<>();
//
//    @OneToOne
//    @JoinColumn(name = "counselorBoardNo")
//    private CounselorBoard boardNo;
//}
