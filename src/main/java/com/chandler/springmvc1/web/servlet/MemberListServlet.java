package com.chandler.springmvc1.web.servlet;

import com.chandler.springmvc1.member.servlet.Member;
import com.chandler.springmvc1.member.servlet.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        // response
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter w = response.getWriter();

        // html 작성
        w.write("<html>");
    }

}
