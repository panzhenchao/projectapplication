package com.it.projectapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class TestController {
    @RequestMapping("/login")
    public String index(Model model){
        return "all-admin-login";
    }
    @PostMapping("/register")
    public String register(@RequestBody Map<String ,Object> body){
        String usertype=body.get("nametype").toString();
        String enterprisetype=body.get("enterprisetype").toString();
        System.out.println(usertype+enterprisetype);
        return "personal-implementation-subject-registration.html";
    }

}
