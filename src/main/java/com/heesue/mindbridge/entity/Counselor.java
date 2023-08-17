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
public class Counselor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counselorNo;

    private String id;

    private String pwd;

    private String name;

    private Long studentNo;

    private Date birth;

    private String description;

    private Date enrollDate;

    @OneToMany(mappedBy = "counselor")
    private List<CounselingRequest> counselingRequestList = new ArrayList<>();
}
