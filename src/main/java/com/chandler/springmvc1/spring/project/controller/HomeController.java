package com.chandler.springmvc1.spring.project.controller;

import com.chandler.springmvc1.spring.project.web.Login;
import com.chandler.springmvc1.spring.project.domain.Member;
import com.chandler.springmvc1.spring.project.domain.MemberRepository;
import com.chandler.springmvc1.spring.project.web.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @GetMapping("/")
    public String homeLogin(@Login Member loginMember, Model model) {
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }


}
