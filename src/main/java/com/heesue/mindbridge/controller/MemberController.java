package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.DTO.Member.MemberDTO;
import com.heesue.mindbridge.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //로그인 페이지
    @GetMapping(value = "/login")
    public String createLoginForm() {
        return "/login";
    }

    //회원가입 페이지
    @GetMapping(value = "/signin")
    public String createSigninForm() {
        return "/signin";
    }

    //회원가입
    @PostMapping(value = "/signin")
    public String signin(@ModelAttribute MemberDTO memberDTO) {
        String newMember = memberService.signin(memberDTO);

        return "redirect:/";
    }

    //아이디 중복 체크
    @PostMapping("/memberIdDup")
    @ResponseBody
    public Boolean checkIdDuplication(@RequestBody MemberDTO memberDTO) {
        Boolean result = memberService.validateDuplicateId(memberDTO.getId());
        return result;
    }

    //학번 중복 체크
    @PostMapping("/memberNoDup")
    @ResponseBody
    public Boolean checkNoDuplication(@RequestBody MemberDTO memberDTO) {
        Boolean result = memberService.validateDuplicateStudentNo(memberDTO.getStudentNo());
        return result;
    }

    @GetMapping("/member/mypage")
    public void mypage(@AuthenticationPrincipal MemberDTO memberDTO) {}


    //학과 리스트
    @GetMapping(value = "majorList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<MajorDTO> findMajorList() {
        return memberService.findAllMajor();
    }

}
