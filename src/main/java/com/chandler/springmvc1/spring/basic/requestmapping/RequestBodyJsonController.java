package com.chandler.springmvc1.spring.basic.requestmapping;

import com.chandler.springmvc1.spring.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RequestBodyJsonController {

    private final ObjectMapper objectMapper;

    @PostMapping("/request-body-json-v1")
    public String requestBodyStringV1(@RequestBody HelloData helloData) {
        log.info("username= {}, age={}",helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    @PostMapping("/request-body-json-v2")
    public String requestBodyStringV2(HttpEntity<HelloData> helloData) {
        HelloData body = helloData.getBody();
        log.info("username= {}, age={}",body.getUsername(), body.getAge());
        return "OK";
    }

    @PostMapping("/request-body-json-v3")
    public HelloData requestBodyStringV3(@RequestBody HelloData data) {
        log.info("username= {}, age={}",data.getUsername(), data.getAge());
        return data;
    }

}
