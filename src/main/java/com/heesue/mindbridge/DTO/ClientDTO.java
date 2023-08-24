package com.heesue.mindbridge.DTO;

import com.heesue.mindbridge.entity.Major;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
public class ClientDTO {
    private String id;

    private String pwd;

    private String name;

    private Long studentNo;

    private Major major;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private String address;
}
