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
public class MajorController {
    private final MajorService majorService;

    //새 학과 추가 폼
    @GetMapping("/admin/major/new")
    public String createForm() {
        return "/admin/major/new";
    }

    //새 학과 추가
    @PostMapping("/admin/major/new")
    public String newMajor(@ModelAttribute MajorDTO majorDTO) {
        majorService.join(majorDTO);
        return "redirect:/admin/major/list";
    }

    //학과 중복 확인
    @PostMapping("/major/nameDup")
    @ResponseBody
    public boolean checkNameDuplication(@RequestBody MajorDTO majorDTO) {
        boolean result = majorService.validateDuplicateName(majorDTO.getName());

        return  result;
    }

    //전체 학과 리스트
    @GetMapping("/admin/major/list")
    public String findAllMajor(@PageableDefault Pageable pageable, Model model) {

        Page<MajorDTO> majorList = majorService.findAllMajor(pageable);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(majorList);

        model.addAttribute("paging", paging);
        model.addAttribute("majorList", majorList);

        return "/admin/major/list";
    }

    @PostMapping("/admin/major/search")
    public String findSearchMajor(@PageableDefault Pageable pageable, Model model, @RequestParam String majorName) {
        Page<MajorDTO> searchList = majorService.findSearchMajor(pageable, majorName);
        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(searchList);

        model.addAttribute("paging", paging);
        model.addAttribute("majorList", searchList);

        return "/admin/major/list";
    }
}
