package com.heesue.mindbridge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Major {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long majorNo;

    private String name;
}
