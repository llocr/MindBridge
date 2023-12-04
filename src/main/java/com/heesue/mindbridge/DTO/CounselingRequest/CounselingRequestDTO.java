package com.heesue.mindbridge.DTO.CounselingRequest;

import com.heesue.mindbridge.entity.CounselorBoard;
import com.heesue.mindbridge.entity.Gender;
import com.heesue.mindbridge.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounselingRequestDTO {
    private Long counselorBoardNo;

    private Boolean nda; // 비밀보장 원칙 동의

    private Boolean notice; // 유의사항 동의

    private String name;

    private Integer grade;

    private String phone;

    private List<String> category; // 고민 유형

    private String description; // 고민 기제

    private String time; // 희망 날짜와 시간

    private List<String> process; // 상담 희망 방식
}
