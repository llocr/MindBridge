package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.ClientDTO;
import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.common.Pagenation;
import com.heesue.mindbridge.common.PagingButtonInfo;
import com.heesue.mindbridge.service.AuthenticationService;
import com.heesue.mindbridge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    protected Authentication createNewAuthentication(Authentication currentAuth, String id) {
        UserDetails newprincipal = authenticationService.loadUserByUsername(id);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newprincipal, currentAuth.getCredentials(), newprincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());

        return newAuth;
    }

}
