package com.chandler.springmvc1.spring.basic.requestmapping;

import com.chandler.springmvc1.spring.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}" , username, age);

        response.getWriter().write("OK");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam String memberName,
                                 @RequestParam int memberAge) {
        log.info("memberName = {}, memberAge = {}" , memberName, memberAge);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(String username, int age) {
        log.info("username = {}, age = {}" , username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(@RequestParam(required = true) String username,
                                 @RequestParam(required = false) Integer age) {
        log.info("username = {}, age = {}" , username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v5")
    public String requestParamV5(@RequestParam(required = true, defaultValue = "guest") String username,
                                 @RequestParam(required = false, defaultValue = "-1") Integer age) {
        log.info("username = {}, age = {}" , username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v6")
    public String requestParamV6(@RequestParam MultiValueMap<String, Object> paramMap) {
        log.info("username = {}, age = {}" , paramMap.get("username"), paramMap.get("age"));
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v7")
    public String requestParamV7(@RequestParam String username, @RequestParam Integer age) {

        HelloData data = new HelloData();

        data.setUsername(username);
        data.setAge(age);

        log.info("username = {}, age = {}", data.getUsername(), data.getAge());
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }


}
