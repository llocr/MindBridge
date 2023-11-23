package com.heesue.mindbridge.DTO.Counselor;

import com.heesue.mindbridge.entity.CounselorBoard;
import com.heesue.mindbridge.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounselorDetailsDTO {
    private String name;
    private String gender;
    private String major;
    private String title;
    private String content;
    private String counselingField;
}
