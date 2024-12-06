package com.chandler.springmvc1.thymeleaf;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/basic")
@Controller
public class BasicThymeleafController {

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-basic";
    }

    @GetMapping("/springEL")
    public String springEL(Model model){

        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> users = new ArrayList<>();
        users.add(userA);
        users.add(userB);

        Map<String, User> userMap = new HashMap<>();
        userMap.put("userA", userA);
        userMap.put("userB", userB);

        model.addAttribute("userA", userA);
        model.addAttribute("users", users);
        model.addAttribute("userMap", userMap);

        return "basic/springEL";
    }

    @GetMapping("/basic-object")
    public String basicObject(HttpSession session){
        session.setAttribute("sessionData", "SESSION!!!");
        return "basic/basic-object";
    }




    }
