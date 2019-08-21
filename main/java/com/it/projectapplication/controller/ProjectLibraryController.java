package com.it.projectapplication.controller;

import com.it.projectapplication.domain.Project;
import com.it.projectapplication.domain.User;
import com.it.projectapplication.serivce.ProjectService;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProjectLibraryController {
    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;
    @RequestMapping(value = "/projectLibrary")
    public ModelAndView projectLibrary(ModelAndView model, HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        User user=userService.findUserByUsername(usernmae);
        List<Project>list=projectService.findProjectByUser(user);
        model.addObject("list",list);
        model.setViewName("all-project-library-manage");
        return model;
    }
}
