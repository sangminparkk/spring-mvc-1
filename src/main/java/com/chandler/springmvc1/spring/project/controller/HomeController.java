package com.chandler.springmvc1.spring.project.controller;

import com.chandler.springmvc1.spring.project.domain.Member;
import com.chandler.springmvc1.spring.project.domain.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberRepository memberRepository;

//    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if (memberId == null) {
            return "home";
        }

        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expiredCookie(response, "memberId");
        return "redirect:/";
    }

    private static void expiredCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
