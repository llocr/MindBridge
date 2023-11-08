package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.Counselor.CounselorMainViewDTO;
import com.heesue.mindbridge.service.CounselorService;
import com.heesue.mindbridge.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CounselorService counselorService;

    @GetMapping(value = {"/", "/main"})
    public String mainPage(Model model) {
        List<CounselorMainViewDTO> counselorList = counselorService.getAllCounselorDetails();

        model.addAttribute("counselorList", counselorList);

        return "/main";
    }

}
