package com.it.projectapplication.controller;

import com.it.projectapplication.domain.AnnouncementManager;
import com.it.projectapplication.serivce.AnnouncementManagerService;
import com.it.projectapplication.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LogoutController {
    @Autowired
    AnnouncementManagerService announcementManagerService;
    @RequestMapping("/logoff")
    public ModelAndView logout(ModelAndView model, HttpServletRequest request, HttpServletResponse response){
        model.setViewName("/all-admin-login");
        CookieUtils.clearCookie(request,response,"tokenHeader");
        List<AnnouncementManager> list=announcementManagerService.findAll();

        model.addObject("list",list);
        return model;

    }
}
