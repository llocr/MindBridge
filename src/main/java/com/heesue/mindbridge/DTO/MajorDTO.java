package com.heesue.mindbridge.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MajorDTO {
    private Long majorNo;
    @NotBlank(message = "학과명을 입력해주세요.")
    private String name;
}
