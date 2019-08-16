package com.it.projectapplication.controller;

import com.it.projectapplication.domain.User;
import com.it.projectapplication.serivce.EnterpriseInformationService;
import com.it.projectapplication.serivce.PersonalInformationService;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.CookieUtils;
import com.it.projectapplication.utils.JwtTokenUtils;
import com.it.projectapplication.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@CrossOrigin(origins = "http://localhost:8080/projectapplication", maxAge = 3600)
@Controller
public class LoginController {

    @Autowired
    EnterpriseInformationService enterpriseInformationService;
    @Autowired
    UserService userService;
    @Autowired
    PersonalInformationService personalInformationService;
    @RequestMapping("/index")
    public String index(Model model){
        return "all-admin-login";
    }
    @RequestMapping("/login/form")
    public ModelAndView login(ModelAndView model, User user, HttpServletRequest request, HttpServletResponse response) throws JSONException ,Exception{


        model.setViewName("/all-admin-login");
        model=userService.login(model,user);
        if("/main".equals(model.getViewName())){
            String url="http://localhost:8080/projectapplication/login";
            JSONObject jsonObject=new JSONObject();
            jsonObject.putOpt("username",user.getUsername());
            jsonObject.putOpt("password",user.getPassword());
            String token= RestTemplateUtils.sendPostRequest(url,jsonObject.toString());
            String tokenHeader=JwtTokenUtils.TOKEN_PREFIX+token;
            model.addObject("permission",JwtTokenUtils.getUserPermission(token));
            model.addObject("token",tokenHeader);
            response.setHeader("token", token);
            CookieUtils.setCookie(response,"tokenHeader",tokenHeader);

        }
        return model;
    }





}
