package com.it.projectapplication.controller;

import com.it.projectapplication.dao.EnterpriseInformationDao;
import com.it.projectapplication.dao.PersonalInformationDao;
import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.EnterpriseInformation;
import com.it.projectapplication.domain.PersonalInformation;
import com.it.projectapplication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TestController {
    @Autowired
    UserDao userDao;
    @Autowired
    PersonalInformationDao personalInformationDao;
    @Autowired
    EnterpriseInformationDao enterpriseInformationDao;
    @RequestMapping("/login")
    public String index(Model model){
        return "all-admin-login";
    }
    @PostMapping("/register")
    public String register(@RequestParam  Map<String ,Object> body,Model model){
        String usertype=body.get("usertype").toString();
        String enterprisetype=body.get("enterprisetype").toString();
        if(usertype.equals("个人")){
            return "personal-implementation-subject-registration.html";
        }
        else {
            model.addAttribute("type",enterprisetype);
            return "enterprise-implementation-subject-registration.html";
        }
    }
    public String registerPersonal(User user, PersonalInformation personalInformation){
        user.setState("0");
        user.setCategory("个人");
        userDao.save(user);
        personalInformation.setUsername(user.getUsername());
        personalInformationDao.save(personalInformation);
        return "all-admin-login";
    }
    public String registerEnterprise(User user, EnterpriseInformation enterpriseInformation){
        user.setState("0");
        user.setCategory("企业");
        userDao.save(user);
        enterpriseInformation.setUsername(user.getUsername());
        enterpriseInformationDao.save(enterpriseInformation);
        return "all-admin-login";

    }


}
