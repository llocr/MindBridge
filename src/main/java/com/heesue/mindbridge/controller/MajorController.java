package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/major")
public class MajorController {
    private final MajorService majorService;

    @GetMapping("/join")
    public String createForm() {
        return "/major/join";
    }

    @PostMapping("/join")
    public String registMajor(@ModelAttribute MajorDTO majorDTO) {
        majorService.join(majorDTO);
        return "redirect:/";
    }

    @PostMapping("/nameDup")
    @ResponseBody
    public boolean checkNameDuplication(@RequestBody MajorDTO majorDTO) {
        boolean result = majorService.validateDuplicateName(majorDTO.getName());

        return  result;
    }
}
