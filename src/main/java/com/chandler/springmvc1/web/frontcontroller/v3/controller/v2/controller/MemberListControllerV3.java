package com.chandler.springmvc1.web.frontcontroller.v3.controller.v2.controller;

import com.chandler.springmvc1.member.servlet.Member;
import com.chandler.springmvc1.member.servlet.MemberRepository;
import com.chandler.springmvc1.web.frontcontroller.ModelView;
import com.chandler.springmvc1.web.frontcontroller.v3.controller.v2.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView modelView = new ModelView("members");
        Map<String, Object> model = modelView.getModel();
        model.put("members", members);

        return modelView;
    }
}
