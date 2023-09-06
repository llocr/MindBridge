package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.entity.Role;
import com.heesue.mindbridge.service.AuthenticationService;
import com.heesue.mindbridge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final AuthenticationService authenticationService;

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
    public String loginPage() {
        return "/client/login";
    }

    @GetMapping("/myPage")
    public String mypage() {
        return "/client/myPage";
    }

    protected Authentication createNewAuthentication(Authentication currentAuth, String id) {
        UserDetails newprincipal = authenticationService.loadUserByUsername(id);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newprincipal, currentAuth.getCredentials(), newprincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());

        return newAuth;
    }

}
