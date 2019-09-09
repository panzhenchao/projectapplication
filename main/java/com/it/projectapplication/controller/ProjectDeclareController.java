package com.it.projectapplication.controller;

import com.it.projectapplication.domain.*;
import com.it.projectapplication.serivce.*;
import com.it.projectapplication.utils.JwtTokenUtils;
import com.it.projectapplication.utils.ProjectNumberUtils;
import com.it.projectapplication.utils.TypeConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ProjectDeclareController {
    @Autowired
    UserService userService;
    @Autowired
    ManagerInformationService managerInformationService;
    @Autowired
    EnterpriseInformationService enterpriseInformationService;
    @Autowired
    SpecialProjectService specialProjectService;

    @Autowired
    PersonalInformationService personalInformationService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectLibraryController projectLibraryController;
    @RequestMapping(value = "/projectDeclare")
    public ModelAndView projectDeclare(ModelAndView model, HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        String category=userService.findCategory(usernmae);
      if("企业".equals(category)){
          category=enterpriseInformationService.findEnterpriseInformationByUsername(usernmae).getType();
       }
        Integer typeNumber= TypeConvert.findTypeNumber(category);
        List<SpecialProject> specialProjects=specialProjectService.findListByDeclareSubject(typeNumber);
        model.addObject("specialProjects",specialProjects);
        model.setViewName("all-project-application-list");
        return model;
    }
    @RequestMapping(value = "/addProjectDeclare")
    public ModelAndView addProjectDeclare(ModelAndView model, HttpServletRequest request,@RequestParam("specialProjectId") Long specialProjectId){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        User user=userService.findUserByUsername(usernmae);
       SpecialProject specialProject=specialProjectService.findSpecoalProjectById(specialProjectId);
       model.addObject("specialProject",specialProject);
        if("企业".equals(user.getCategory())){
           EnterpriseInformation enterpriseInformation=enterpriseInformationService.findEnterpriseInformationByUsername(usernmae);
            model.addObject("user",enterpriseInformation);
            model.setViewName("enterprise-entity-new-project-declaration");

       }
       else if("个人".equals(user.getCategory())){
           PersonalInformation personalInformation=personalInformationService.findPersonalInformationByUsername(usernmae);
           model.addObject("user",personalInformation);
            model.setViewName("personal-entity-new-project-declaration");

        }
        Project project=new Project();
        project.setSpecialProject(specialProject);
        Long maxNumber=projectService.findMaxNumber();
        project.setNumber(ProjectNumberUtils.getProjectNumber(maxNumber));
        project.setUser(user);
        projectService.save(project);
        model.addObject("project",project);
        return model;
    }
    @RequestMapping(value = "/saveProjectDeclare")
    public ModelAndView savePorjectDeclare(ModelAndView model,HttpServletRequest request,Project project){
        System.out.println(project.getNumber());
        model.setViewName("all-project-application-list");
        Project oldProject=projectService.findProjectById(project.getId());
        project.setUser(oldProject.getUser());
        project.setSpecialProject(oldProject.getSpecialProject());
        Date date=new Date(System.currentTimeMillis());
        project.setSubmitDate(date);
        projectService.save(project);
        projectLibraryController.projectLibrary(model,request);
        return model;
    }
}
