package com.heesue.mindbridge.DTO;

import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.entity.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Callable;

@Getter @Setter
@ToString
public class ClientDTO implements UserDetails {
    private String id;

    private String password;

    private String name;

    private Long studentNo;

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
