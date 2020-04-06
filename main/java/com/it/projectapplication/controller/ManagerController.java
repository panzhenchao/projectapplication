package com.it.projectapplication.controller;

import com.it.projectapplication.domain.ManagerInformation;
import com.it.projectapplication.serivce.ManagerInformationService;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    ManagerInformationService managerInformationService;


    @GetMapping("/findAllManager")
    public ModelAndView findAllManager(ModelAndView model, HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        List<ManagerInformation> list=managerInformationService.findAll();
        model.addObject("list",list);
        model.setViewName("/admin-manager-list");
        return model;
    }
    @PostMapping("/addManagerUrl")
    public ModelAndView addManagerUrl(ModelAndView model, HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.setViewName("/admin-manager-add");
        return model;
    }
}
