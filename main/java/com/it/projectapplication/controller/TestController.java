package com.it.projectapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/test2");
        modelAndView.addObject("name", "老王");
        return modelAndView;
    }
    @RequestMapping("/2/")
    public String index2(){
        return "/test";
    }

}
