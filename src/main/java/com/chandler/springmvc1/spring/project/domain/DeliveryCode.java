package com.chandler.springmvc1.spring.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * FAST, NORMAL, SLOW
 */
@Getter @Setter
@AllArgsConstructor
public class DeliveryCode {

    private String code;
    private String displayName;

}
