package com.chandler.springmvc1.spring.project.controller;

import com.chandler.springmvc1.spring.project.domain.Member;
import com.chandler.springmvc1.spring.project.domain.MemberRepository;
import com.chandler.springmvc1.spring.project.dto.MemberSaveForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("member", new Member());
        return "/members/addMemberForm";
    }

    @PostMapping("/add")
    public String saveMember(@Valid @ModelAttribute("member") MemberSaveForm memberSaveForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult);
            return "/members/addMemberForm";
        }

        Member member = new Member(memberSaveForm.getLoginId(), memberSaveForm.getName(), memberSaveForm.getPassword());
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "redirect:/"; // PRG pattern
    }

}
