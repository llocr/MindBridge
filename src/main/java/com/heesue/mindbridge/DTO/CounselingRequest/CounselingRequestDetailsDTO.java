package com.heesue.mindbridge.DTO.CounselingRequest;

import com.heesue.mindbridge.entity.ApprovalStatus;
import com.heesue.mindbridge.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounselingRequestDetailsDTO {
    private Member member;
    private Integer grade;
    private String phone;
    private String category;
    private String description;
    private String time;
    private String process;
    private ApprovalStatus status;
    private LocalDateTime appliedDateTime;
}
