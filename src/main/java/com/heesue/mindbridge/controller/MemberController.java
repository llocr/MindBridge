package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.DTO.Member.MemberDTO;
import com.heesue.mindbridge.DTO.Member.MemberListDTO;
import com.heesue.mindbridge.common.Pagenation;
import com.heesue.mindbridge.common.PagingButtonInfo;
import com.heesue.mindbridge.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    //마이페이지
    @GetMapping("/member/mypage")
    public void mypage(@AuthenticationPrincipal MemberDTO memberDTO) {}

    //멤버 리스트 보기
    @GetMapping("/admin/member/list")
    public String findAllMember(@PageableDefault Pageable pageable, Model model) {
        Page<MemberListDTO> memberList = memberService.findAllMember(pageable);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(memberList);

        model.addAttribute("paging", paging);
        model.addAttribute("memberList", memberList);

        return "/admin/member/list";
    }

    //멤버 리스트에서 이름 검색
    @GetMapping("/admin/member/search")
    public String findSearchMember(@PageableDefault Pageable pageable, Model model, @RequestParam String memberName) {
        Page<MemberListDTO> memberList = memberService.findSearchMember(pageable, memberName);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(memberList);

        model.addAttribute("paging", paging);
        model.addAttribute("memberList", memberList);

        return "/admin/member/list";
    }

    //학과 리스트
    @GetMapping(value = "majorList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<MajorDTO> findMajorList() {
        return memberService.findAllMajor();
    }

}
