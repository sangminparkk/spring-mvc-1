package com.chandler.springmvc1.spring.project.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("do filter = {}");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String user = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}][{}]", user, requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            log.info("RESPONSE [{}][{}]", user, requestURI);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
        Filter.super.destroy();
    }
}
