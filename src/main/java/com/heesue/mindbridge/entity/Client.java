package com.heesue.mindbridge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientNo;

    private String id;

    private String pwd;

    private String name;

    private Long studentNo;

    @Enumerated(value = EnumType.STRING)
    private Major major;

    private Date birth;

    private String address;

    private Date enrollDate;

    @OneToMany(mappedBy = "client")
    private List<CounselingRequest> counselingRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "CBWriter")
    private List<ClientBoard> boardList = new ArrayList<>();
}
