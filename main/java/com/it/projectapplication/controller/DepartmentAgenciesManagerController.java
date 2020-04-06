package com.it.projectapplication.controller;

import com.it.projectapplication.domain.DepartmentInformation;
import com.it.projectapplication.serivce.DepartmentInformationService;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DepartmentAgenciesManagerController {

    @Autowired
    DepartmentInformationService departmentInformationService;


    @GetMapping("/addDepartmentUrl")
    public ModelAndView addDepartmentUrl(ModelAndView model,HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.setViewName("/admin-department-add");
        return model;
    }


    @PostMapping("/addDepartment")
    public ModelAndView addDepartment(ModelAndView model, HttpServletRequest request,DepartmentInformation departmentInformation){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.setViewName("/admin-department-add");
        DepartmentInformation departmentInformation1=departmentInformationService.findDepartmentByDepartmentName(departmentInformation.getDepartmentName());
        if(departmentInformation1!=null){
            String msg="新增部门失败，部门名称已经存在";
            model.addObject("msg",msg);
            return model;
        }
      if(departmentInformation!=null){
            departmentInformationService.save(departmentInformation);
            String msg="新增部门成功";
             model.addObject("msg",msg);
          return model;
      }
      return model;
    }
    @GetMapping("/findAllDepartment")
    public ModelAndView findAllDepartment(ModelAndView model,HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        List<DepartmentInformation> list=departmentInformationService.findAllDepartment();
        model.addObject("list",list);
        model.setViewName("/admin-department-list");
        return model;
    }


}
