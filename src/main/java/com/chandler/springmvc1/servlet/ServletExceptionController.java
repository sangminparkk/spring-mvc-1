package com.chandler.springmvc1.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@Slf4j
@Controller
public class ServletExceptionController {

    @GetMapping("/error-ex")
    public void servletException() {
        throw new RuntimeException("servletException");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(SC_NOT_FOUND, "Not Found, 404 에러!!");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(SC_INTERNAL_SERVER_ERROR, "내부 서버 오류, 500 에러!!");
    }

}
