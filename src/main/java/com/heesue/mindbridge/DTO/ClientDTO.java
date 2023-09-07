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

@Getter @Setter
@ToString
public class ClientDTO implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.name()));

        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료되지 않음
    }

    // 계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠기지 않음
    }

    // 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료되지 않음
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true; // 활성화
    }
}
