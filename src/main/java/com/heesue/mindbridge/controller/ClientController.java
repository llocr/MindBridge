package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.CounselingRequest.CounselingRequestDTO;
import com.heesue.mindbridge.DTO.Member.MemberDTO;
import com.heesue.mindbridge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    //상담 신청 페이지
    @GetMapping("/client/request/{counselorBoardNo}")
    public String counselingApplyForm(@PathVariable Long counselorBoardNo, Model model, @AuthenticationPrincipal MemberDTO memberDTO) {
        model.addAttribute("counselorBoardNo", counselorBoardNo);
        model.addAttribute("member", memberDTO);
        return "/client/request";
    }

    //상담 신청
    @PostMapping("/client/request")
    public String counselingApply(@ModelAttribute CounselingRequestDTO counselingRequestDTO,
                                  @RequestParam("counselorBoardNo") Long counselorBoardNo,Principal loggedMember) {

        clientService.counselingApply(counselingRequestDTO, counselorBoardNo, loggedMember);
        return "redirect:/main";
    }
}
