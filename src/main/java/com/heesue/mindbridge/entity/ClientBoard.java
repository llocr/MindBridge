//package com.heesue.mindbridge.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table
//public class ClientBoard extends BaseEntity {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long clientBoardNo;
//
//    @ManyToOne
//    @JoinColumn(name = "clientNo")
//    private Client writer;
//
//    private String title;
//
//    private String content;
//
//    @Enumerated(value = EnumType.STRING)
//    private BoardStatus status;
//}
