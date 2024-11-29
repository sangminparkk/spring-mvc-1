package com.chandler.springmvc1.web.frontcontroller.v3.controller.v2;

import com.chandler.springmvc1.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);

}
