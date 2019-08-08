package com.it.projectapplication.controller;

import com.it.projectapplication.dao.EnterpriseInformationDao;
import com.it.projectapplication.dao.PersonalInformationDao;
import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.EnterpriseInformation;
import com.it.projectapplication.domain.PersonalInformation;
import com.it.projectapplication.domain.User;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.RandomUtils;
import com.it.projectapplication.utils.SaveUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    UserDao userDao;
    @Autowired
    PersonalInformationDao personalInformationDao;
    @Autowired
    EnterpriseInformationDao enterpriseInformationDao;
    @Autowired
    UserService userService;
    @RequestMapping("/index")
    public String index(Model model){
        return "all-admin-login";
    }
    @RequestMapping("/login/form")
    public ModelAndView login(ModelAndView model, User user){
        System.out.println("jinxl");
        model=userService.login(model,user);
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
    public String registerPersonal(User user, PersonalInformation personalInformation, @RequestParam("identityCardImgFile")MultipartFile file) throws IOException {
        user.setState("0");
        user.setCategory("个人");

        personalInformation.setUsername(user.getUsername());
        personalInformation.setIdentityCardImg(RandomUtils.getRandomString()+file.getOriginalFilename());
        if(null!=file) {
            personalInformation.setIdentityCardImg(SaveUploadUtils.getIdentityCardImgDirFile(file, SaveUploadUtils.IDENTITY_CARD));
        }
        userDao.save(user);
        personalInformationDao.save(personalInformation);
        return "all-admin-login";
    }
    @PostMapping("/registerEnterprise")
    public String registerEnterprise(User user, EnterpriseInformation enterpriseInformation,@RequestParam("businessLicenseImgFile")MultipartFile bLFile ,@RequestParam("corporateIdentityCardImgFile")MultipartFile cIFile ,@RequestParam("industryElse") String industryElse)throws IOException{
        user.setState("0");
        user.setCategory("企业");
        userDao.save(user);
        enterpriseInformation.setUsername(user.getUsername());
        if(null!=industryElse){
            enterpriseInformation.setIndustry(industryElse);
        }
        if(null!=bLFile && null!=cIFile) {
            enterpriseInformation.setBusinessLicenseImg(SaveUploadUtils.getIdentityCardImgDirFile(bLFile, SaveUploadUtils.BUSINESS_LICENSE));
            enterpriseInformation.setCorporateIdentityCardImg(SaveUploadUtils.getIdentityCardImgDirFile(cIFile, SaveUploadUtils.IDENTITY_CARD));
        }
        userDao.save(user);
        enterpriseInformationDao.save(enterpriseInformation);
        return "all-admin-login";

    }


}
