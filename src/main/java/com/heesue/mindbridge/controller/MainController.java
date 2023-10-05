package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping(value = {"/", "/main"})
    public String mainPage() {
        return "/main";
    }

}
