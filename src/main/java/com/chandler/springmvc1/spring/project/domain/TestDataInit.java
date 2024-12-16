package com.chandler.springmvc1.spring.project.domain;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        memberRepository.save(new Member("qqqq1111", "chandler", "123456798"));
        memberRepository.save(new Member("qwer2222", "vibe", "a456456"));
    }
}
