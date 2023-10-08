package com.heesue.mindbridge.DTO.Member;

import com.heesue.mindbridge.entity.Gender;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberListDTO {
    private String id;

    private String name;

    private Gender gender;

    private Long studentNo;

    private String email;

    private Major major;

    private Date birth;

    private String address;

    private Role role;
}
