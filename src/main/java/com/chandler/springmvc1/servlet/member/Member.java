package com.chandler.springmvc1.servlet.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor // 이게 왜?
public class Member {

    private Long id;
    private String username;
    private int age;

    @Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
