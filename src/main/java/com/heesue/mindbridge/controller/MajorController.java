package com.heesue.mindbridge.controller;

import com.heesue.mindbridge.DTO.MajorDTO;
import com.heesue.mindbridge.common.Pagenation;
import com.heesue.mindbridge.common.PagingButtonInfo;
import com.heesue.mindbridge.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/major")
public class MajorController {
    private final MajorService majorService;

//    @GetMapping("/new")
//    public String createForm() {
//        return "/admin/major/create";
//    }

    @PostMapping("/new")
    public String newMajor(@ModelAttribute MajorDTO majorDTO) {
        majorService.join(majorDTO);
        return "redirect:/major/list";
    }

    @PostMapping("/nameDup")
    @ResponseBody
    public boolean checkNameDuplication(@RequestBody MajorDTO majorDTO) {
        boolean result = majorService.validateDuplicateName(majorDTO.getName());

        return  result;
    }

    @GetMapping("/list")
    public String findAllMajor(@PageableDefault Pageable pageable, Model model) {

        Page<MajorDTO> majorList = majorService.findAllMajor(pageable);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(majorList);

        model.addAttribute("paging", paging);
        model.addAttribute("majorList", majorList);

        return "/admin/major/list";
    }

    @PostMapping("/list")
    public String findSearchMajor(@PageableDefault Pageable pageable, Model model, @RequestParam String majorName) {
        Page<MajorDTO> searchList = majorService.findSearchMajor(pageable, majorName);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(searchList);

        model.addAttribute("paging", paging);
        model.addAttribute("majorList", searchList);

        return "/admin/major/list";
    }
}
