package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.CounselorApplyDTO;
import com.heesue.mindbridge.entity.Counselor;
import com.heesue.mindbridge.entity.Member;
import com.heesue.mindbridge.repository.CounselorRepository;
import com.heesue.mindbridge.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CounselorService {
    private final CounselorRepository counselorRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    //상담사 자격 신청
    @Transactional
    public Member newCounselorApply(CounselorApplyDTO counselorApplyDTO, Principal loggedMember) {

        Counselor counselor = modelMapper.map(counselorApplyDTO, Counselor.class);

        String loggedMemberID = loggedMember.getName();
        Member member = memberRepository.findById(loggedMemberID)
                .orElseThrow(() -> new RuntimeException("Logged in Member not found"));

        counselor.setCounselorId(member);
        counselor.setAccept(false);

        counselorRepository.save(counselor);

        return counselor.getCounselorId();
    }
}
