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
public class ClientBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientBoardNo;

    @ManyToOne
    @JoinColumn(name = "clientNo")
    private Client CBWriter;

    private String title;

    private String body;

    private int count;

    private Date createdDate;

    private Date modifiedDate;

    @Enumerated(value = EnumType.STRING)
    private BoardStatus status;
}
