package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.CounselorApplyDTO;
import com.heesue.mindbridge.service.CounselorService;
import com.heesue.mindbridge.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CounselorController {
    private final CounselorService counselorService;
    private final MemberService memberService;

    //상담사 자격 신청 페이지
    @GetMapping("/member/apply")
    public String counselorApplyForm() {
        return "/member/apply";
    }

    //상담사 자격 신청
    @PostMapping("/member/apply")
    public String counselorApply(@ModelAttribute CounselorApplyDTO counselorApplyDTO) {
        String newCounselor = counselorService.newCounselorApply(counselorApplyDTO);

        return "redirect:/";
    }
}
