package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/admin/home")
    public String adminHome() {
        return "/admin/home";
    }

}
