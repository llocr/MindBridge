package com.heesue.mindbridge.DTO.Member;

import com.heesue.mindbridge.entity.Gender;
import com.heesue.mindbridge.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    private Gender gender;

    @NotNull(message = "학번을 입력해주세요.")
    @Positive(message = "학번은 양수여야 합니다.")
    private Long studentNo;

    @Email
    private String email;

    private Major major;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private String address;
}
