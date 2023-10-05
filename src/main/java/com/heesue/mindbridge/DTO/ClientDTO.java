package com.heesue.mindbridge.DTO;

import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.entity.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Getter @Setter
@ToString
public class ClientDTO {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "학번을 입력해주세요.")
    @Positive(message = "학번은 양수여야 합니다.")
    private Long studentNo;

    @Email
    private String email;

    private Major major;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private String address;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime enrollDate;

    private Role role;

    public ClientDTO(String id, String password, String name, Long studentNo, String email, Major major, Date birth, String address, LocalDateTime enrollDate, Role role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.studentNo = studentNo;
        this.email = email;
        this.major = major;
        this.birth = birth;
        this.address = address;
        this.enrollDate = enrollDate;
        this.role = role;
    }

    public ClientDTO() {
    }

}
