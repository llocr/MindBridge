package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.DTO.Member.MemberDTO;
import com.heesue.mindbridge.DTO.Member.MemberListDTO;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.entity.Member;
import com.heesue.mindbridge.entity.Role;
import com.heesue.mindbridge.repository.MajorRepository;
import com.heesue.mindbridge.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    //학과 조회
    public List<MajorDTO> findAllMajor() {
        List<Major> majorList = majorRepository.findAll(Sort.by("majorNo"));

        return majorList.stream().map(major -> modelMapper.map(major, MajorDTO.class)).collect(Collectors.toList());
    }

    //겹치는 아이디 조회
    public boolean validateDuplicateId(String id) {
        return memberRepository.existsMemberById(id);
    }

    //겹치는 학번 조회
    public boolean validateDuplicateStudentNo(Long studentNo) {
        return memberRepository.existsMemberByStudentNo(studentNo);
    }

    //회원가입
    @Transactional
    public String signin(MemberDTO memberDTO) {

        Member member = modelMapper.map(memberDTO, Member.class);

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setEnrollDate(LocalDateTime.now());
        member.setRole(Role.ROLE_CLIENT);

        memberRepository.save(member);

        return member.getId();
    }

    //멤버 리스트 조회
    public Page<MemberListDTO> findAllMember(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("name"));

        Page<Member> memberList = memberRepository.findAll(pageable);

        return memberList.map(member -> modelMapper.map(member, MemberListDTO.class));
    }

    //검색 멤버 조회
    public Page<MemberListDTO> findSearchMember(Pageable pageable, String memberName) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("name"));

        Page<Member> memberList = memberRepository.findMemberByNameContaining(pageable, memberName);


        return memberList.map(member -> modelMapper.map(member, MemberListDTO.class));

    }

    //회원 정보 수정
    @Transactional
    public void editMember(MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberDTO.getId()).orElseThrow(IllegalAccessError::new);

        member.setName(memberDTO.getName());
        member.setMajor(memberDTO.getMajor());
        member.setEmail(memberDTO.getEmail());
        member.setBirth(memberDTO.getBirth());
        member.setAddress(memberDTO.getAddress());

        updateSecurityContext(member);
    }

    //세션 정보 업데이트
    private void updateSecurityContext(Member member) {
        Authentication updatedauthentication = new UsernamePasswordAuthenticationToken(
                member,
                null,
                member.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(updatedauthentication);
    }
}
