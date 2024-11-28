package com.chandler.springmvc1.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = "/webapp/WEB-INF/views/" + viewPath + ".jsp"; // prefix/postfix 적용
    }

    //TODO: render 호출 누가?
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(this.viewPath);
        requestDispatcher.forward(request, response);
    }

}
