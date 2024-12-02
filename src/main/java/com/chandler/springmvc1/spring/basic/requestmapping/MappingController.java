package com.chandler.springmvc1.spring.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
public class MappingController {

    @GetMapping("/hello-basic")
    public String hello() {
        log.info("hello");
        return "OK, Hello Basic";
    }

    @GetMapping("/mapping/{userId}")
    public String findByUserId(@PathVariable Long userId) {
        log.info("userId = {}" , userId);
        return "OK" + userId;
    }

    /**
     * 다중 사용 가능
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String findByUserId(@PathVariable Long userId, @PathVariable Long orderId) {
        log.info("userId = {}, orderId = {}" , userId, orderId);
        return "OK " + userId + " orderID = " + orderId;
    }

    @PostMapping(value = "/mapping-consume", consumes = APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes" );
        return "OK";
    }

}
