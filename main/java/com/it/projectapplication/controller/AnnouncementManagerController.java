package com.it.projectapplication.controller;

import com.it.projectapplication.domain.AnnouncementManager;
import com.it.projectapplication.serivce.AnnouncementManagerService;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AnnouncementManagerController {
    @Autowired
    AnnouncementManagerService announcementManagerService;


    @GetMapping("/findAnnouncementList")
    public ModelAndView fundAnnouncementList(ModelAndView model, HttpServletRequest request){
        List<AnnouncementManager> list=announcementManagerService.findAll();
        String token= JwtTokenUtils.getToken(request);
        String username=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.addObject("list",list);
        model.setViewName("/department-of-announcement-manager-list");
        return model;
    }
    @GetMapping("/addAnnouncementUrl")
    public ModelAndView addAnnouncementUrl(ModelAndView model, HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.setViewName("/department-of-announcement-manager-add");
        return model;
    }
    @PostMapping("/addAnnouncement")
    public ModelAndView addAnnouncement(ModelAndView model, HttpServletRequest request,AnnouncementManager announcementManager ){

        String token= JwtTokenUtils.getToken(request);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.setViewName("/department-of-announcement-manager-add");
        model.addObject("msg","发布成功");
        announcementManagerService.save(announcementManager);
        return model;
    }




}
