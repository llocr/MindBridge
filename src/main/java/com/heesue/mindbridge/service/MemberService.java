package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.DTO.Member.MemberDTO;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.entity.Member;
import com.heesue.mindbridge.entity.Role;
import com.heesue.mindbridge.repository.MajorRepository;
import com.heesue.mindbridge.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MajorRepository majorRepository;
    private final MemberRepository memberRespository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    //학과 조회
    public List<MajorDTO> findAllMajor() {
        List<Major> majorList = majorRepository.findAll(Sort.by("majorNo"));

        return majorList.stream().map(major -> modelMapper.map(major, MajorDTO.class)).collect(Collectors.toList());
    }

    //겹치는 아이디 조회
    public boolean validateDuplicateId(String id) {
        return memberRespository.existsMemberById(id);
    }

    //겹치는 학번 조회
    public boolean validateDuplicateStudentNo(Long studentNo) {
        return memberRespository.existsMemberByStudentNo(studentNo);
    }

    //회원가입
    @Transactional
    public String signin(MemberDTO memberDTO) {

        Member member = modelMapper.map(memberDTO, Member.class);

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setEnrollDate(LocalDateTime.now());
        member.setRole(Role.ROLE_CLIENT);

        memberRespository.save(member);

        return member.getId();
    }


}
