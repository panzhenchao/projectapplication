package com.it.projectapplication.controller;

import com.it.projectapplication.domain.*;
import com.it.projectapplication.serivce.ManagerInformationService;
import com.it.projectapplication.serivce.ProjectService;
import com.it.projectapplication.serivce.SpecialFundService;
import com.it.projectapplication.serivce.UserService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProjectApprovalController {
    @Autowired
    SpecialFundService specialFundService;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @Autowired
    ManagerInformationService managerInformationService;
    @RequestMapping(value = "/projectApproval")
    public ModelAndView projectApproval(ModelAndView model, HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5" )Integer size){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        User user=userService.findUserByUsername(usernmae);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        String department="";
        if("乡镇部门".equals(user.getCategory())){
            ManagerInformation managerInformation =managerInformationService.findManagerInformationByUserName(usernmae);


            Pageable pageable=new PageRequest(page-1,size);
            Page<SpecialFund> sPage=specialFundService.findSpecialFunds(pageable);
            List<SpecialFund> specialFundList=sPage.getContent();
            for(SpecialFund specialFund:specialFundList){
                for(SpecialProject specialProject:specialFund.getSpecialProjects()){
                    List list=projectService.findProjectsByAddressIsLike("%"+managerInformation.getDepartment()+"%");
                    Set<Project> projects=new HashSet<>(list);
                    specialProject.setProjects(projects);
                }
            }
            model.addObject("totalElements",sPage.getTotalElements());
            model.addObject("list",specialFundList);
            model.addObject("totalPages",sPage.getTotalPages());
            model.addObject("size",size);
            model.addObject("currentPage",sPage.getNumber()+1);
            model.setViewName("department-of-village-project-manage");
        }
        return model;
    }
    public ModelAndView villageProjectApproval(ModelAndView model, HttpServletRequest request,@RequestParam("projectId") Long projectId){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        Project project=projectService.findProjectById(projectId);
        model.addObject("project",project);
        model.setViewName("department-of-village-exaim-enterprise-declaration");
        return model;

    }
}
