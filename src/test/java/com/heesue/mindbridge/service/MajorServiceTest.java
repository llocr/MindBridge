package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.repository.MajorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MajorServiceTest {
    @Autowired
    MajorService majorService;
    @Autowired
    MajorRepository majorRepository;

    @Test
    public void 학과_추가() throws Exception {
        //given
        MajorDTO major = new MajorDTO();
        major.setName("상담심리학과");

        //when
        Long joinId = majorService.join(major);

        //then
        Major findMajor = majorRepository.findById(joinId).get();
        Assertions.assertEquals(major.getName(), findMajor.getName());
    }
}