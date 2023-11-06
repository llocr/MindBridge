package com.heesue.mindbridge.DTO.CounselorBoard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounselorBoardDTO {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "자신을 소개하는 글을 입력해주세요.")
    private String content;

    @NotBlank(message = "사용하는 상담기법성 입력해주세요.")
    private String counselingField;
}
