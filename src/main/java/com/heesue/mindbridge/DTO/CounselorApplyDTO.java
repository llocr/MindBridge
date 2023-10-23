package com.heesue.mindbridge.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounselorApplyDTO {

    private Boolean clubCheck;

    @NotBlank(message = "간단한 자기소개를 입력해주세요.")
    private String description;
}
