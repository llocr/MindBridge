package com.heesue.mindbridge.DTO.CounselingRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounselingRequestListDTO {
    private Long counselingRequestNo;
    private String name;
    private String category;
    private LocalDateTime appliedDateTime;
}
