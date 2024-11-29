package com.chandler.springmvc1.web.frontcontroller.v3.controller.v2.controller;

import com.chandler.springmvc1.web.frontcontroller.ModelView;
import com.chandler.springmvc1.web.frontcontroller.v3.controller.v2.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
