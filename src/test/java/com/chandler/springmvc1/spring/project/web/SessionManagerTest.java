package com.chandler.springmvc1.spring.project.web;

import com.chandler.springmvc1.spring.project.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionManagerTest {

    @Autowired
    SessionManager sessionManager;

    @Test
    public void sessionTest() {
        // 세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        // 요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // 세션 조회
        Object session = sessionManager.getSession(request);
        assertEquals(member, session);

        // 세션 만료
        sessionManager.expire(request);
        Object expiredSession = sessionManager.getSession(request);
        assertNotEquals(member, expiredSession);
        assertNull(expiredSession);
    }
}
