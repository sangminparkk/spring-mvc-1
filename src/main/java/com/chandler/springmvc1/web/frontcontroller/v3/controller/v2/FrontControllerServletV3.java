package com.chandler.springmvc1.web.frontcontroller.v3.controller.v2;

import com.chandler.springmvc1.web.frontcontroller.ModelView;
import com.chandler.springmvc1.web.frontcontroller.MyView;
import com.chandler.springmvc1.web.frontcontroller.v3.controller.v2.controller.MemberFormControllerV3;
import com.chandler.springmvc1.web.frontcontroller.v3.controller.v2.controller.MemberListControllerV3;
import com.chandler.springmvc1.web.frontcontroller.v3.controller.v2.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() { // 생성시에 초기화
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
}

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(SC_NOT_FOUND);
            return;
        }

        // paramMap > 이렇게 풀어내지 못했고. 기존의 메서드에서만 해결하려고 보니 안됐음ㅠㅠ
        Map<String, String> paramMap = createParamMap(request);
        ModelView modelView = controller.process(paramMap);

        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);

        view.render(modelView.getModel(), request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/webapp/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
