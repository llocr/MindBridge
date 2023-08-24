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
    @JoinColumn(name = "clientNo")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "counselorNo")
    private Counselor counselor;

    private String description;

    private boolean accept;
}
