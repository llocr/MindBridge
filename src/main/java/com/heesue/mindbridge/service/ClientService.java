package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.CounselingRequest.CounselingRequestDTO;
import com.heesue.mindbridge.entity.*;
import com.heesue.mindbridge.repository.CounselingRequestRepository;
import com.heesue.mindbridge.repository.CounselorBoardRepository;
import com.heesue.mindbridge.repository.CounselorRepository;
import com.heesue.mindbridge.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClientService {
    private final MemberRepository memberRepository;
    private final CounselorRepository counselorRepository;
    private final CounselingRequestRepository counselingRequestRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public void counselingApply(CounselingRequestDTO counselingRequestDTO, Long counselorBoardNo, Principal loggedMember) {
        Member member = memberRepository.findById(loggedMember.getName())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        Counselor counselor = counselorRepository.findByBoardNo_CounselorBoardNo(counselorBoardNo)
                .orElseThrow(() -> new IllegalArgumentException("상담사 정보를 찾을 수 없습니다."));

        CounselingRequest counselingRequest = modelMapper.map(counselingRequestDTO, CounselingRequest.class);

        counselingRequest.setCategory(String.join(",", counselingRequestDTO.getCategory()));
        counselingRequest.setProcess(String.join(",", counselingRequestDTO.getProcess()));

        counselingRequest.setMember(member);
        counselingRequest.setCounselor(counselor);
        counselingRequest.setMajor(member.getMajor());
        counselingRequest.setGender(member.getGender());
        counselingRequest.setStatus(ApprovalStatus.PENDING);
        counselingRequest.setAppliedDateTime(LocalDateTime.now());

        counselingRequestRepository.save(counselingRequest);
    }
}
