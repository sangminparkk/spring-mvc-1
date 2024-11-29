package com.chandler.springmvc1.web.frontcontroller.v3.controller.v2.controller;

import com.chandler.springmvc1.member.servlet.Member;
import com.chandler.springmvc1.member.servlet.MemberRepository;
import com.chandler.springmvc1.web.frontcontroller.ModelView;
import com.chandler.springmvc1.web.frontcontroller.v3.controller.v2.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 파라미터
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView modelView = new ModelView("save");
        Map<String, Object> model = modelView.getModel();
        model.put("member", member);

        return modelView;
    }
}
