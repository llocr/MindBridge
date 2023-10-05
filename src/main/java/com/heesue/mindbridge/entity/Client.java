package com.heesue.mindbridge.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table
public class Client {
    @Id
    private String id;

    private String password;

    private String name;

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


//    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
//    private List<CounselingRequest> counselingRequestList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
//    private List<ClientBoard> boardList = new ArrayList<>();
}
