package com.it.projectapplication.controller;

import com.it.projectapplication.domain.EnterpriseInformation;
import com.it.projectapplication.domain.ManagerInformation;
import com.it.projectapplication.domain.PersonalInformation;
import com.it.projectapplication.domain.User;
import com.it.projectapplication.serivce.EnterpriseInformationService;
import com.it.projectapplication.serivce.ManagerInformationService;
import com.it.projectapplication.serivce.PersonalInformationService;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OrderManageController {
    @Autowired
    ManagerInformationService managerInformationService;
    @Autowired
    PersonalInformationService personalInformationService;
    @Autowired
    EnterpriseInformationService enterpriseInformationService;
    @Autowired
    UserService userService;
    @GetMapping("/personalOrderManger")
    public ModelAndView personalOrderManger(ModelAndView model, HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        String token= JwtTokenUtils.getToken(request);
        String username= JwtTokenUtils.getUsername(token);
        ManagerInformation managerInformation=managerInformationService.findManagerInformationByUserName(username);
        Pageable pageable=new PageRequest(page-1,size);
        String address="%"+managerInformation.getDepartment()+"%";

        Page<PersonalInformation> pPage=personalInformationService.findPersonalInformationByaddressAndPageble(address,pageable);
        model.addObject("totalElements",pPage.getTotalElements());
        model.addObject("list",pPage.getContent());
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.addObject("totalPages",pPage.getTotalPages());
        model.addObject("size",size);
        model.addObject("currentPage",pPage.getNumber()+1);
        model.setViewName("/personal-order-manage-list");
        return model;
    }
    @GetMapping("/enterpriseOrderManger")
    public ModelAndView enterpriseOrderManger(ModelAndView model, HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        String token= JwtTokenUtils.getToken(request);
        String username= JwtTokenUtils.getUsername(token);
        ManagerInformation managerInformation=managerInformationService.findManagerInformationByUserName(username);
        Pageable pageable=new PageRequest(page-1,size);
        String address="%"+managerInformation.getDepartment()+"%";
        Page<EnterpriseInformation> ePage=enterpriseInformationService.findEnterpriseInformationByaddressAndPageble(address,pageable);
        model.addObject("totalElements",ePage.getTotalElements());
        model.addObject("list",ePage.getContent());
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.addObject("totalPages",ePage.getTotalPages());
        model.addObject("size",size);
        model.addObject("currentPage",ePage.getNumber()+1);
        model.setViewName("/enterprise-order-manage-list");
        return model;
    }
    @GetMapping("/changeUserState")
    public ModelAndView changeUserState(ModelAndView model, HttpServletResponse response, HttpServletRequest request, @RequestParam("username")String username, @RequestParam("state")String state,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        String token=JwtTokenUtils.getToken(request);
        User user=userService.findUserByUsername(username);
        user.setState(state);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        if("企业".equals(user.getCategory())){
            EnterpriseInformation enterpriseInformation=enterpriseInformationService.findEnterpriseInformationByUsername(username);
            enterpriseInformation.setState(state);
            enterpriseOrderManger(model,request,page,size);
            return model;
        }else if("个人".equals(user.getCategory())){
            PersonalInformation personalInformation=personalInformationService.findPersonalInformationByUsername(username);
            personalInformation.setState(state);
            personalOrderManger(model,request,page,size);
            return model;
        }
        return model;
    }
    @RequestMapping("/resetPassword")
    public ModelAndView resetPassword(ModelAndView model, HttpServletRequest request,@RequestParam("username")String username,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        String token=JwtTokenUtils.getToken(request);
        User user=userService.findUserByUsername(username);
        user.setPassword("123456");
        userService.saveUser(user);
        model.addObject("msg","重置用户名为："+username+"密码成功");
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        if("企业".equals(user.getCategory())){
            model.setViewName("/enterprise-order-manage-list");
            enterpriseOrderManger(model,request,page,size);
            return model;
        }else if("个人".equals(user.getCategory())){
            model.setViewName("/personal-order-manage-list");
            personalOrderManger(model,request,page,size);
            return model;

        }
        return model;

    }

}
