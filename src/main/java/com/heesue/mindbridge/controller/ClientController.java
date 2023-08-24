package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.entity.Major;
import com.heesue.mindbridge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());

        return "client/createClientForm";
    }

    @PostMapping("/new")
    public String registClient(@Validated ClientDTO clientDTO) {
        clientService.join(clientDTO);
        return "redirect:/";
    }

    @GetMapping(value = "major", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MajorDTO> findMajorList(){
        return clientService.findAllMajor();
    }



}
