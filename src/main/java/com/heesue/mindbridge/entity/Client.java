package com.heesue.mindbridge.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Id
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "학번을 입력해주세요.")
    @Min(value = 1, message = "학번은 양수여야 합니다.")
    private Long studentNo;

    private String email;

    @ManyToOne
    @JoinColumn(name = "majorNo")
    private Major major;

    @Temporal(TemporalType.DATE)
    private Date birth;

    private String address;

    private LocalDateTime enrollDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<CounselingRequest> counselingRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<ClientBoard> boardList = new ArrayList<>();
}
