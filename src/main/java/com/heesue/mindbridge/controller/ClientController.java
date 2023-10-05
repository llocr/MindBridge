package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.DTO.LoginRequestDTO;
import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.common.Pagenation;
import com.heesue.mindbridge.common.PagingButtonInfo;
import com.heesue.mindbridge.repository.ClientRepository;
import com.heesue.mindbridge.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientRepository clientRepository;

    private final ClientService clientService;

    @GetMapping("/join")
    public String createForm(){
        return "/client/join";
    }

    @PostMapping("/join")
    public String registClient(@ModelAttribute ClientDTO clientDTO) {
        clientService.join(clientDTO);
        return "redirect:/";
    }

    @PostMapping("/clientIdDup")
    @ResponseBody
    public Boolean checkIdDuplication(@RequestBody ClientDTO clientDTO) {
        boolean result = clientService.validateDuplicateClientId(clientDTO.getId());
        return result;
    }

    @PostMapping("/clientNoDup")
    @ResponseBody
    public boolean checkNoDuplication(@RequestBody ClientDTO clientDTO) {
        boolean result = clientService.validateDuplicateClientSudentNo(clientDTO.getStudentNo());
        return result;
    }

    @GetMapping(value = "/major", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MajorDTO> findMajorList(){
        return clientService.findAllMajor();
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/client/login";
    }

//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequestDTO loginRequestDTO) {
//        return clientService.login(loginRequestDTO);
//        String userid = loginRequest.get("userid");
//        String password = loginRequest.get("password");
//
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userid, password));
//
//        UserDetails userDetails = clientRepository.load
//    }

    @GetMapping("/myPage")
    public String mypage() {
        return "/client/myPage";
    }

    @GetMapping("/list")
    public String findAllClient(@PageableDefault Pageable pageable, Model model) {
        Page<ClientDTO> clientList = clientService.findAllClient(pageable);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(clientList);

        model.addAttribute("paging", paging);
        model.addAttribute("clientList", clientList);

        return "/client/list";
    }

    @PostMapping("/list")
    public String findSearchClient(@PageableDefault Pageable pageable, Model model, @RequestParam String clientName) {
        Page<ClientDTO> searchList = clientService.findSearchClient(pageable, clientName);

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(searchList);

        model.addAttribute("paging", paging);
        model.addAttribute("clientList", searchList);

        return "client/list";
    }

    @GetMapping("/edit/{id}")
    public String modifyClientLoad(@PathVariable String id, Model model) {
        ClientDTO client = clientService.findClientById(id);

        model.addAttribute("client", client);

        return "client/edit";
    }

    @PostMapping("/edit")
    public String modifyClientSave(ClientDTO clientDTO ) {
        clientService.modifyClient(clientDTO);
        return "redirect:/";
    }

}
