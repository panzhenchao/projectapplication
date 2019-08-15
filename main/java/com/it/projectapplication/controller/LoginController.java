package com.it.projectapplication.controller;

import com.it.projectapplication.domain.EnterpriseInformation;
import com.it.projectapplication.domain.PersonalInformation;
import com.it.projectapplication.domain.User;
import com.it.projectapplication.serivce.EnterpriseInformationService;
import com.it.projectapplication.serivce.PersonalInformationService;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
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
    @PostMapping("/registerPersonal")
    public ModelAndView registerPersonal(ModelAndView moldel,User user, PersonalInformation personalInformation, @RequestParam("identityCardImgFile")MultipartFile file) throws IOException {
        user.setState("0");
        user.setCategory("个人");

        personalInformation.setUsername(user.getUsername());
        personalInformation.setIdentityCardImg(RandomUtils.getRandomString()+file.getOriginalFilename());
        personalInformation.setState("0");
        if(null!=file) {
            personalInformation.setIdentityCardImg(SaveUploadUtils.getSaveDirFile(file, SaveUploadUtils.IDENTITY_CARD));
        }
        userService.saveUser(user);
        personalInformationService.savePersonalInformation(personalInformation);
        moldel.setViewName("/all-admin-login");
        moldel.addObject("msg","注册成功!审核通过后方可登录");
        return moldel;
    }
    @PostMapping("/registerEnterprise")
    public ModelAndView registerEnterprise(ModelAndView moldel,User user, EnterpriseInformation enterpriseInformation,@RequestParam("businessLicenseImgFile")MultipartFile bLFile ,@RequestParam("corporateIdentityCardImgFile")MultipartFile cIFile ,@RequestParam("industryElse") String industryElse)throws IOException{
        user.setState("0");
        user.setCategory("企业");
        userService.saveUser(user);
        enterpriseInformation.setUsername(user.getUsername());
        enterpriseInformation.setState("0");
        if(null!=industryElse){
            enterpriseInformation.setIndustry(industryElse);
        }
        if(null!=bLFile && null!=cIFile) {
            enterpriseInformation.setBusinessLicenseImg(SaveUploadUtils.getSaveDirFile(bLFile, SaveUploadUtils.BUSINESS_LICENSE));
            enterpriseInformation.setCorporateIdentityCardImg(SaveUploadUtils.getSaveDirFile(cIFile, SaveUploadUtils.IDENTITY_CARD));
        }
        enterpriseInformationService.saveEnterpriseInformation(enterpriseInformation);
        enterpriseInformation.setType("企业");
        moldel.setViewName("/all-admin-login");
        moldel.addObject("msg","注册成功!审核通过后方可登录");
        return moldel;

    }


}
