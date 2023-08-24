package com.heesue.mindbridge.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = {"/", "/main"})
    public String mainPage() {
        return "/main";
    }
}
