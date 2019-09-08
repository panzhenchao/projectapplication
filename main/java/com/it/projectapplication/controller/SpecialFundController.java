package com.it.projectapplication.controller;

import com.it.projectapplication.domain.SpecialFund;
import com.it.projectapplication.serivce.ManagerInformationService;
import com.it.projectapplication.serivce.SpecialFundService;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SpecialFundController {
    @Autowired
    SpecialFundService specialFundService;
    @Autowired
    ManagerInformationService managerInformationService;
    @RequestMapping(value = "/specialFundMange")
    public ModelAndView specialFundMange(ModelAndView model, HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        String token= JwtTokenUtils.getToken(request);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        Pageable pageable=new PageRequest(page-1,size);
        Page<SpecialFund> sPage=specialFundService.findSpecialFunds(pageable);
        model.addObject("totalElements",sPage.getTotalElements());
        model.addObject("list",sPage.getContent());
        model.addObject("totalPages",sPage.getTotalPages());
        model.addObject("size",size);
        model.addObject("currentPage",sPage.getNumber()+1);
        model.setViewName("department-of-manger-special-fund-mange");
        return model;
    }
    @RequestMapping(value = "/addSpecialFund")
    public ModelAndView addSpecialFund(ModelAndView model,HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.setViewName("special-fund-subject-registration.html");
        return model;
    }
    @RequestMapping(value = "/successAddFund")
    public ModelAndView addSpecialFundProject(ModelAndView model, HttpServletRequest request, SpecialFund specialFund){
        String token= JwtTokenUtils.getToken(request);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        specialFund.setState("0");
        specialFund.setDepartmentName(managerInformationService.findManagerInformationByUserName(JwtTokenUtils.getUsername(token)).getDepartmentInformation().getDepartmentName());
        specialFundService.save(specialFund);
        specialFundMange(model,request, 1,5);
        model.setViewName("department-of-manger-special-fund-mange");
        return model;
    }
    @RequestMapping(value = "/specialProjectManage")
    public ModelAndView specialManage(ModelAndView model, HttpServletRequest request,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        model=specialFundMange(model,request, page,size);
        model.setViewName("department-of-manger-special-project-manage");
        return model;
    }

}
