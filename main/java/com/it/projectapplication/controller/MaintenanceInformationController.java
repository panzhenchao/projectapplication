package com.it.projectapplication.controller;

import com.it.projectapplication.domain.EnterpriseInformation;
import com.it.projectapplication.domain.PersonalInformation;
import com.it.projectapplication.serivce.EnterpriseInformationService;
import com.it.projectapplication.serivce.PersonalInformationService;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.CookieUtils;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:8080/projectapplication", maxAge = 3600)
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
    @GetMapping("/changePassword")
    public ModelAndView change(ModelAndView model,HttpServletRequest request){
        model.setViewName("/user-password-change");
        String token=JwtTokenUtils.getToken(request);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        return model;
    }
    @PostMapping("/changePassword/form")

    public ModelAndView changePassword(ModelAndView model, HttpServletResponse response,HttpServletRequest request, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String newPassword2){
        Cookie []cookies=request.getCookies();
        model.setViewName("/user-password-change");
        if(!newPassword.equals(newPassword2)){
            model.addObject("msg","2次密码不一致");
            return model;
        }
        String token=JwtTokenUtils.getToken(request);
        if(null==token){
            model.addObject("msg","请求错误");
            return model;
        }
        String username= JwtTokenUtils.getUsername(token);
        if(userService.validatePassword(username,oldPassword)){
            userService.changePassword(username,newPassword);
            model.addObject("msg","修改密码成功重新登录");
            model.setViewName("/all-admin-login");
            CookieUtils.clearCookie(request,response,"tokenHeader");
        }else{
            model.addObject("msg","修改密码失败，登录密码错误");
        }

        return model;
    }
}
