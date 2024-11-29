package com.chandler.springmvc1.spring.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/member/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }

}
