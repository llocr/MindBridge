package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.Counselor.CounselorApplyDTO;
import com.heesue.mindbridge.DTO.Counselor.CounselorDetailsDTO;
import com.heesue.mindbridge.DTO.Counselor.CounselorViewDTO;
import com.heesue.mindbridge.DTO.CounselorBoard.CounselorBoardDTO;
import com.heesue.mindbridge.common.Pagenation;
import com.heesue.mindbridge.common.PagingButtonInfo;
import com.heesue.mindbridge.entity.Member;
import com.heesue.mindbridge.service.CounselorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CounselorController {
    private final CounselorService counselorService;

    //상담자 페이지 들어갈 때 상담 신청 건수 불러오기
    @GetMapping("/counselor/home")
    public String counselorHome(Principal loggedMember, Model model) {
        Long counselingReqListCount = counselorService.findCounselingReqList(loggedMember);

        model.addAttribute("requestCount", counselingReqListCount);

        return "/counselor/home";
    }

    //상담사 자격 신청 페이지
    @GetMapping("/member/apply")
    public String counselorApplyForm() {
        return "/member/apply";
    }

    //상담사 자격 신청
    @PostMapping("/member/apply")
    public String counselorApply(@ModelAttribute CounselorApplyDTO counselorApplyDTO, Principal loggedMember) {
        counselorService.newCounselorApply(counselorApplyDTO, loggedMember);

        return "redirect:/";
    }

    //상담사 자격 신청 리스트 보기
    @GetMapping("/admin/counselor/apply")
    public String pendingCounselorList(@PageableDefault Pageable pageable, Model model) {
        Page<CounselorViewDTO> pendingCounselorList = counselorService.findPendingCounselor(pageable);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(pendingCounselorList);

        model.addAttribute("paging", paging);
        model.addAttribute("counselorList", pendingCounselorList);

        return "/admin/counselor/apply";
    }

    //상담사 자격 신청 디테일
    @GetMapping("/admin/counselor/{counselorNo}")
    public String viewCounselorDetails(@PathVariable Long counselorNo, Model model) {
        CounselorViewDTO counselor = counselorService.findCounselor(counselorNo);

        model.addAttribute("counselor", counselor);

        return "/admin/counselor/detail";
    }

    //상담사 자격 신청 수락
    @PostMapping("/admin/counselor/accept/{counselorNo}")
    public String acceptCounselor(@PathVariable Long counselorNo) {
        counselorService.acceptCounselor(counselorNo);

        return "redirect:/admin/counselor/apply";
    }

    //상담사 자격 신청 거절
    @PostMapping("/admin/counselor/reject/{counselorNo}")
    public String rejectCounselor(@PathVariable Long counselorNo) {
        counselorService.rejectCounselor(counselorNo);

        return "redirect:/admin/counselor/apply";
    }

    //상담사 정보 입력 페이지
    @GetMapping("/counselor/details")
    public String creatDetailsForm() {
        return "/counselor/details";
    }

    //상담자 정보 입력
    @PostMapping("/counselor/details")
    public String writeCounselorDetails(@ModelAttribute CounselorBoardDTO counselorBoardDTO, Principal loggedMember) {
        counselorService.writeCounselorDetails(counselorBoardDTO, loggedMember);

        return "redirect:/";
    }

    //상담자 정보 상세보기
    @GetMapping("/main/counselor/details/{counselorBoardNo}")
    public String showCounselorDetails(@PathVariable Long counselorBoardNo, Model model) {
        CounselorDetailsDTO counselor = counselorService.getCounselorDetails(counselorBoardNo);

        model.addAttribute("counselor", counselor);
        return "main/counselor/details";
    }

}
