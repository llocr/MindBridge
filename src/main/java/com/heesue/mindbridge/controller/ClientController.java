package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.entity.Role;
import com.heesue.mindbridge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
//    private final MessageSourceAccessor messageSourceAccessor;

    @GetMapping("/join")
    public String createForm(){
        return "/client/join";
    }

    @PostMapping("/join")
    public String registClient(@ModelAttribute ClientDTO clientDTO) {
        clientService.join(clientDTO);
        return "redirect:/";
    }

    @GetMapping(value = "major", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MajorDTO> findMajorList(){
        return clientService.findAllMajor();
    }

    @GetMapping("/login")
    public void loginPage() {
    }

//    @GetMapping("/mypage")
//    public void mypage(@AuthenticationPrincipal ClientDTO client) {
//    }

}
