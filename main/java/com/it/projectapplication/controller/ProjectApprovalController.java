package com.it.projectapplication.controller;

import com.it.projectapplication.domain.*;
import com.it.projectapplication.serivce.ManagerInformationService;
import com.it.projectapplication.serivce.ProjectService;
import com.it.projectapplication.serivce.SpecialFundService;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.JwtTokenUtils;
import com.it.projectapplication.utils.MyCompartor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        String username=JwtTokenUtils.getUsername(token);
        User user=userService.findUserByUsername(username);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        String department="";
        if("乡镇部门".equals(user.getCategory())){
            ManagerInformation managerInformation =managerInformationService.findManagerInformationByUserName(username);


            Pageable pageable=new PageRequest(page-1,size);
            Page<SpecialFund> sPage=specialFundService.findSpecialFunds(pageable);
            List<SpecialFund> specialFundList=sPage.getContent();
            for(SpecialFund specialFund:specialFundList){
                for(SpecialProject specialProject:specialFund.getSpecialProjects()){
                    List list=projectService.findProjectsByAddressIsLikeAndSpecialProjectAndState("%"+managerInformation.getDepartment()+"%",specialProject,1);
                    Set<Project> projects=new TreeSet<>(new MyCompartor());
                    projects.addAll(list);
                    specialProject.setProjects(projects);
                }
            }
            model.addObject("totalElements",sPage.getTotalElements());
            model.addObject("list",specialFundList);
            model.addObject("totalPages",sPage.getTotalPages());
            model.addObject("size",size);
            model.addObject("currentPage",sPage.getNumber()+1);
            model.addObject("villageUsername",username);
            model.setViewName("department-of-village-project-manage");
        }else if("主管部门".equals(user.getCategory())){
            ManagerInformation managerInformation =managerInformationService.findManagerInformationByUserName(username);
            Pageable pageable=new PageRequest(page-1,size);
            Page<SpecialFund> ePage=specialFundService.findSpecialFundsByDepartment(managerInformation.getDepartment(),pageable);
            List<SpecialFund> specialFundList=ePage.getContent();
            for(SpecialFund specialFund:specialFundList){
                for(SpecialProject specialProject:specialFund.getSpecialProjects()){
                    List list=projectService.findProjectsByVillageDepartmentStateAndSpecialProject(1,specialProject);
                    Set<Project> projects=new TreeSet<>(new MyCompartor());
                    projects.addAll(list);
                    specialProject.setProjects(projects);
                }
            }
            model.addObject("totalElements",ePage.getTotalElements());
            model.addObject("list",ePage.getContent());
            model.addObject("totalPages",ePage.getTotalPages());
            model.addObject("size",size);
            model.addObject("currentPage",ePage.getNumber()+1);
            model.addObject("manageName",username);
            model.setViewName("department-of-manage-project-manage");


        }
        return model;
    }
    @RequestMapping(value = "/departmentProjectApproval")
    public ModelAndView departmentProjectApproval(ModelAndView model, HttpServletRequest request,@RequestParam("projectId") Long projectId,@RequestParam("method") String method){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        User user=userService.findUserByUsername(usernmae);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        Project project=projectService.findProjectById(projectId);
        model.addObject("project",project);
        if("乡镇部门".equals(user.getCategory())) {
            if ("edit".equals(method)) {
                model.setViewName("department-of-village-exaim-enterprise-declaration");
            } else if ("check".equals(method)) {
                model.setViewName("department-of-village-check-enterprise-declaration");
            }
        }else if("主管部门".equals(user.getCategory())){
            if ("edit".equals(method)) {
                model.setViewName("department-of-manage-exaim-enterprise-declaration");
            } else if ("check".equals(method)) {
                model.setViewName("department-of-village-check-enterprise-declaration");
            }
        }


        return model;

    }
    @RequestMapping(value = "/changeVillageState")
    public ModelAndView villageStateChange(ModelAndView model,HttpServletRequest request,@RequestParam("projectId") Long projectId,@RequestParam("villageDepartmentState") Integer villageDepartmentState){
        Project project=projectService.findProjectById(projectId);
        String token= JwtTokenUtils.getToken(request);
        String username=JwtTokenUtils.getUsername(token);
        project.setVillageName(username);
        project.setVillageDepartmentState(villageDepartmentState);
        projectService.save(project);
        if(1==villageDepartmentState){
            model.addObject("msg","推荐成功");
        }
        else if(2==villageDepartmentState){
            model.addObject("msg","备选成功");
        }
        departmentProjectApproval(model,request,projectId,"edit");
        return model;
    }
    @RequestMapping(value = "/changeManageState")
    public ModelAndView manageStateChange(ModelAndView model,HttpServletRequest request,@RequestParam("projectId") Long projectId,@RequestParam("manageDepartmentState") Integer manageDepartmentState){
        Project project=projectService.findProjectById(projectId);
        String token= JwtTokenUtils.getToken(request);
        String username=JwtTokenUtils.getUsername(token);
        project.setManageName(username);
        project.setManageDepartmentState(manageDepartmentState);
        projectService.save(project);
        if(1==manageDepartmentState){
            model.addObject("msg","通过");
        }
        else if(2==manageDepartmentState){
            model.addObject("msg","备选成功");
        }else if(3==manageDepartmentState){
            model.addObject("msg","不受理");
        }else if(4==manageDepartmentState){
            model.addObject("msg","退回修改");
        }
        departmentProjectApproval(model,request,projectId,"edit");
        return model;
    }
    @RequestMapping(value = "/planManager")
    public ModelAndView planManager(ModelAndView model ,HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5" )Integer size){

        String token= JwtTokenUtils.getToken(request);
        String username=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        User user=userService.findUserByUsername(username);
        if("主管部门".equals(user.getCategory())){
            ManagerInformation managerInformation =managerInformationService.findManagerInformationByUserName(username);
            Pageable pageable=new PageRequest(page-1,size);
            Page<SpecialFund> ePage=specialFundService.findSpecialFundsByDepartment(managerInformation.getDepartment(),pageable);
            List<SpecialFund> specialFundList=ePage.getContent();
            for(SpecialFund specialFund:specialFundList){
                for(SpecialProject specialProject:specialFund.getSpecialProjects()){
                    List list=projectService.findProjectsByManageDepartmentStateAndSpecialProject(1,specialProject);
                    Set<Project> projects=new TreeSet<>(new MyCompartor());
                    projects.addAll(list);
                    specialProject.setProjects(projects);
                }
            }
            model.addObject("totalElements",ePage.getTotalElements());
            model.addObject("list",ePage.getContent());
            model.addObject("totalPages",ePage.getTotalPages());
            model.addObject("size",size);
            model.addObject("currentPage",ePage.getNumber()+1);
            model.addObject("manageName",username);
            model.setViewName("department-of-manage-implement-plan-manage");

        }
        return model;

    }
    @RequestMapping(value = "/changeFundState")
    public ModelAndView fundStateChange(ModelAndView model ,HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5" )Integer size,@RequestParam("projectId") Long projectId){
        String token= JwtTokenUtils.getToken(request);
        String username=JwtTokenUtils.getUsername(token);
        Project project=projectService.findProjectById(projectId);
        project.setFundDepartmentState(1);
        projectService.save(project);
        return model;
    }


}
