package com.it.projectapplication.controller;

import com.it.projectapplication.domain.*;
import com.it.projectapplication.serivce.*;
import com.it.projectapplication.utils.CookieUtils;
import com.it.projectapplication.utils.JwtTokenUtils;
import com.it.projectapplication.utils.SaveUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    @Autowired
    AuditPersonalInformationService auditPersonalInformationService;
    @Autowired
    AuditEnterpriseInformationService auditEnterpriseInformationService;
    @GetMapping("/changeInformation")
    public ModelAndView changeInformation(ModelAndView model, HttpServletResponse response,HttpServletRequest request){
        String token=JwtTokenUtils.getToken(request);
        String username= JwtTokenUtils.getUsername(token);
        String category=userService.findCategory(username);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        if("企业".equals(category)){
            EnterpriseInformation enterpriseInformation=enterpriseInformationService.findEnterpriseInformationByUsername(username);
            model.addObject("enterpriseInformation",enterpriseInformation);
            model.setViewName("/enterprise-implementation-subject-information-change");
        }else if("个人".equals(category)){
            PersonalInformation personalInformation=personalInformationService.findPersonalInformationByUsername(username);
            model.addObject("personalInformation",personalInformation);
            model.setViewName("/personal-implementation-subject-information-change");

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
    @PostMapping("/changePersonalInformation")
    public ModelAndView changePersonalInformation(ModelAndView model, HttpServletResponse response, HttpServletRequest request, AuditPersonalInformation auditPersonalInformation,@RequestParam("identityCardImgFile") MultipartFile identityCardImgFile){
        String token=JwtTokenUtils.getToken(request);
        String username= JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.setViewName("/personal-implementation-subject-information-change");
        PersonalInformation personalInformation=personalInformationService.findPersonalInformationByUsername(username);
        model.addObject("personalInformation",personalInformation);
        AuditPersonalInformation oldAuditPersonalInformation=auditPersonalInformationService.findAuditPersonInformationByUsername(username);
        if(null!=oldAuditPersonalInformation){
            model.addObject("msg","信息已经提交等待审核后方可再次修改");

            return model;
        }
        auditPersonalInformation.setAuditType(0);
        if(null!=identityCardImgFile){
            auditPersonalInformation.setIdentityCardImg(SaveUploadUtils.getSaveDirFile(identityCardImgFile,SaveUploadUtils.IDENTITY_CARD));
            auditPersonalInformationService.saveAuditPersonalInformation(auditPersonalInformation);
        }
        model.setViewName("/personal-implementation-subject-information-change");
        model.addObject("msg","信息已经提交等待审核");
        return model;
    }
    @PostMapping("/changeEnterpriseInformation")
    public ModelAndView changeEnterpriseInformation(ModelAndView model, HttpServletResponse response, HttpServletRequest request, AuditEnterpriseInformation auditEnterpriseInformation ,@RequestParam("businessLicenseImgFile") MultipartFile businessLicenseImgFile,@RequestParam("businessLicenseImgFile") MultipartFile corporateIdentityCardImgFile ,@RequestParam("industryElse") String industryElse){
        String token=JwtTokenUtils.getToken(request);
        String username= JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.setViewName("/enterprise-implementation-subject-information-change");
        auditEnterpriseInformation.setAuditType(0);
        EnterpriseInformation enterpriseInformation=enterpriseInformationService.findEnterpriseInformationByUsername(username);
        model.addObject("enterpriseInformation",enterpriseInformation);
        AuditEnterpriseInformation oldAuditEnterpriseInformation=auditEnterpriseInformationService.findAuitEnterpriseInformationByUsername(username);
        if(null!=oldAuditEnterpriseInformation){
            model.addObject("msg","信息已经提交等待审核后方可再次修改");
            return model;
        }
        auditEnterpriseInformation.setAuditType(0);
        if(null!=businessLicenseImgFile){
            auditEnterpriseInformation.setBusinessLicenseImg(SaveUploadUtils.getSaveDirFile(businessLicenseImgFile,SaveUploadUtils.BUSINESS_LICENSE));
        }
        if(null!=corporateIdentityCardImgFile){
            auditEnterpriseInformation.setCorporateIdentityCardImg(SaveUploadUtils.getSaveDirFile(corporateIdentityCardImgFile,SaveUploadUtils.IDENTITY_CARD));
        }
        if(null!=industryElse){
            enterpriseInformation.setIndustry(industryElse);
        }
        model.setViewName("/enterprise-implementation-subject-information-change");
        model.addObject("msg","信息已经提交等待审核");
        return model;

    }
    @GetMapping("/checkInformation")
    public ModelAndView checkPersonalInformation(ModelAndView model, HttpServletResponse response, HttpServletRequest request,@RequestParam("username") String username){
        String token=JwtTokenUtils.getToken(request);
        String category=userService.findCategory(username);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        if("企业".equals(category)){
            EnterpriseInformation enterpriseInformation=enterpriseInformationService.findEnterpriseInformationByUsername(username);
            model.addObject("enterpriseInformation",enterpriseInformation);
            model.setViewName("/enterprise-implementation-subject-information-check");
        }else if("个人".equals(category)){
            PersonalInformation personalInformation=personalInformationService.findPersonalInformationByUsername(username);
            model.addObject("personalInformation",personalInformation);
            model.setViewName("/personal-implementation-subject-information-check");

        }
        return model;
    }


}
