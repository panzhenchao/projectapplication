package com.it.projectapplication.controller;

import com.it.projectapplication.domain.EnterpriseInformation;
import com.it.projectapplication.domain.ManagerInformation;
import com.it.projectapplication.domain.PersonalInformation;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
        model.setViewName("/enterprise-order-manage-list");
        return model;
    }
}
