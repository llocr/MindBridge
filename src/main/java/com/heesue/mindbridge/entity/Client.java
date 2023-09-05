package com.heesue.mindbridge.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientNo;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$", message = "아이디를 3-12자로 입력해주세요. [특수문자 X]")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$", message = "비밀번호를 3-12자로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "학번을 입력해주세요.")
    private Long studentNo;

    private String email;

    @ManyToOne
    @JoinColumn(name = "majorNo")
    private Major major;

    private Date birth;

    private String address;

    private LocalDateTime enrollDate;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<CounselingRequest> counselingRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<ClientBoard> boardList = new ArrayList<>();

    @Builder
    public Client(String id, String password, String name, Long studentNo, String email, Major major, Date birth, String address, LocalDateTime enrollDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.studentNo = studentNo;
        this.email = email;
        this.major = major;
        this.birth = birth;
        this.address = address;
        this.enrollDate = enrollDate;
    }

    protected Client(){}
}
