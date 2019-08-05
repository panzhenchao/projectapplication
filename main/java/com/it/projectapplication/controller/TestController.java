package com.it.projectapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView model= new ModelAndView("/admin-administrative-region-manage.html");

        return model;
    }
}
