package com.heesue.mindbridge.DTO.Counselor;

import com.heesue.mindbridge.entity.Counselor;
import com.heesue.mindbridge.entity.CounselorBoard;
import com.heesue.mindbridge.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounselorMainViewDTO {
    private Member counselorId;

    private CounselorBoard boardNo;

}
