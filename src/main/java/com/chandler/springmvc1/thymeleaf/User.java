package com.chandler.springmvc1.thymeleaf;

import lombok.Getter;

@Getter
public class User {

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
