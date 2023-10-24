package com.heesue.mindbridge.DTO.Counselor;

import com.heesue.mindbridge.entity.ApprovalStatus;
import com.heesue.mindbridge.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounselorViewDTO {
    private Long counselorNo;

    private Member counselorId;

    private Boolean clubCheck;

    private String description;

    private ApprovalStatus approvalStatus;

    private LocalDateTime appliedDateTime;
}
