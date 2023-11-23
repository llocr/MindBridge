package com.heesue.mindbridge.service;

import com.heesue.mindbridge.DTO.Counselor.CounselorApplyDTO;
import com.heesue.mindbridge.DTO.Counselor.CounselorDetailsDTO;
import com.heesue.mindbridge.DTO.Counselor.CounselorMainViewDTO;
import com.heesue.mindbridge.DTO.Counselor.CounselorViewDTO;
import com.heesue.mindbridge.DTO.CounselorBoard.CounselorBoardDTO;
import com.heesue.mindbridge.entity.*;
import com.heesue.mindbridge.repository.CounselorBoardRepository;
import com.heesue.mindbridge.repository.CounselorRepository;
import com.heesue.mindbridge.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CounselorService {
    private final CounselorRepository counselorRepository;
    private final CounselorBoardRepository counselorBoardRepository;
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
        counselor.setApprovalStatus(ApprovalStatus.PENDING);
        counselor.setAppliedDateTime(LocalDateTime.now());


        counselorRepository.save(counselor);

        return counselor.getCounselorId();
    }

    //상담사 자격 신청 리스트
    public Page<CounselorViewDTO> findPendingCounselor(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("appliedDateTime"));

        Page<Counselor> counselorList = counselorRepository.findByApprovalStatus(ApprovalStatus.PENDING, pageable);

        return counselorList.map(counselor -> modelMapper.map(counselor, CounselorViewDTO.class));
    }

    //상담사 자격 신청 자세히 보기
    public CounselorViewDTO findCounselor(Long counselorNo) {
        Counselor findCounselor = counselorRepository.findById(counselorNo)
                .orElseThrow(() -> new RuntimeException("Find Counselor not found"));

        return modelMapper.map(findCounselor,
                CounselorViewDTO.class);
    }

    //상담 자격 신청 수락
    @Transactional
    public void acceptCounselor(Long counselorNo) {
        Counselor findCounselor = counselorRepository.findById(counselorNo)
                .orElseThrow(() -> new RuntimeException("Find Counselor not found"));

        findCounselor.setApprovalStatus(ApprovalStatus.ACCEPTED);

        Member findMember = memberRepository.findById(findCounselor.getCounselorId().getId())
                .orElseThrow(() -> new RuntimeException("Find Counselor not found"));

        findMember.setRole(Role.ROLE_COUNSELOR);
    }

    //상담 자격 신청 거절
    @Transactional
    public void rejectCounselor(Long counselorNo) {
        Counselor findCounselor = counselorRepository.findById(counselorNo)
                .orElseThrow(() -> new RuntimeException("Find Counselor not found"));

        findCounselor.setApprovalStatus(ApprovalStatus.REJECTED);
    }

    @Transactional
    //상담사 정보 입력
    public void writeCounselorDetails(CounselorBoardDTO counselorBoardDTO, Principal loggedMember) {
        CounselorBoard counselorBoard = modelMapper.map(counselorBoardDTO, CounselorBoard.class);

        String loggedMemberId = loggedMember.getName();
        Member member = memberRepository.findById(loggedMemberId)
                .orElseThrow(() -> new RuntimeException("Find Member not found"));
        Counselor counselor = counselorRepository.findByCounselorId(member)
                .orElseThrow(() -> new RuntimeException("Counselor not found"));


        counselorBoard.setCounselor(counselor);
        counselorBoard.setCount(0L);
        counselorBoard.setStatus(BoardStatus.PUBLISHED);

        counselorBoardRepository.save(counselorBoard);

        counselor.setBoardNo(counselorBoard);
    }

    //상담자 매인 정보 가져오기
    public List<CounselorMainViewDTO> getAllCounselorDetails() {
        List<Counselor> counselorBoardList = counselorRepository.findByBoardNoIsNotNull();

        List<CounselorMainViewDTO> counselorList = counselorBoardList.stream()
                .map(counselor -> modelMapper.map(counselor, CounselorMainViewDTO.class))
                .collect(Collectors.toList());

        return counselorList;
    }

    //상담자 상세 정보 가져오기
    public CounselorDetailsDTO getCounselorDetails(Long counselorBoardNo) {
        CounselorDetailsDTO counselor = new CounselorDetailsDTO();
        CounselorBoard counselorBoard = counselorBoardRepository.findById(counselorBoardNo)
                .orElseThrow(() -> new RuntimeException("Counselor not found"));

        Member member = memberRepository.findById(counselorBoard.getCounselor().getCounselorId().getId())
                .orElseThrow(() -> new RuntimeException("Find Member not found"));

        counselor.setName(member.getName());
        counselor.setGender(member.getGender().getValue());
        counselor.setMajor(member.getMajor().getName());
        counselor.setTitle(counselorBoard.getTitle());
        counselor.setContent(counselorBoard.getContent());
        counselor.setCounselingField(counselorBoard.getCounselingField());

        return counselor;
    }
}
