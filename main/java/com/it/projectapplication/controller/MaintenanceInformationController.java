package com.it.projectapplication.controller;

import com.it.projectapplication.domain.EnterpriseInformation;
import com.it.projectapplication.domain.PersonalInformation;
import com.it.projectapplication.serivce.EnterpriseInformationService;
import com.it.projectapplication.serivce.PersonalInformationService;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MaintenanceInformationController {
    @Autowired
    UserService userService;
    @Autowired
    EnterpriseInformationService enterpriseInformationService;
    @Autowired
    PersonalInformationService personalInformationService;

    public ModelAndView changeInformation(ModelAndView model ,String token){
        String username= JwtTokenUtils.getUsername(token);
        String category=userService.findCategory(username);
        if("企业".equals(category)){
            EnterpriseInformation enterpriseInformation=enterpriseInformationService.findInformationByUsername(username);
            model.addObject("enterpriseInformation",enterpriseInformation);
        }else if("个人".equals(category)){
            PersonalInformation personalInformation=personalInformationService.findPersonalInformationByUsername(username);
            model.addObject("personalInformation",personalInformation);

        }
        return model;
    }
    public ModelAndView changePassword(ModelAndView model, String token, @RequestParam String oldPassowrd,@RequestParam String newPassword){
        String username= JwtTokenUtils.getUsername(token);
        if(userService.validatePassword(username,oldPassowrd)){
            userService.changePassword(username,newPassword);
            model.addObject("msg","修改密码成功");
        }else{
            model.addObject("msg","修改密码失败，登录密码错误");
        }
        model.setViewName("/user-password-change");
        return model;
    }
}
