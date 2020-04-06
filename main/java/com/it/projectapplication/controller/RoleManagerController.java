package com.it.projectapplication.controller;

import com.it.projectapplication.domain.Role;
import com.it.projectapplication.serivce.RoleService;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class RoleManagerController {
    @Autowired
    RoleService roleService;




    @GetMapping("/addRoleUrl")
    public ModelAndView addRoleUrl(ModelAndView model, HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.setViewName("/admin-role-add");
        return model;
    }
    @GetMapping("/findAllRole")
    public ModelAndView findAllRole(ModelAndView model, HttpServletRequest request){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        List<Role> list=roleService.findAll();
        model.addObject("list",list);
        model.setViewName("/admin-role-list");
        return model;
    }
    @GetMapping("/checkRoleInformation")
    public ModelAndView findRole(ModelAndView model,HttpServletRequest request,@PathParam("roleId")String roleId){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        Role role=roleService.findRole(Integer.parseInt(roleId));
        model.addObject(role);
        model.setViewName("/admin-role-edit");
        return model;
    }

    @PostMapping("/editRolePerssion")
    public ModelAndView editRole(ModelAndView model, HttpServletRequest request, @PathParam("permission")String permission,@PathParam("roleId")String roleid){
        String token= JwtTokenUtils.getToken(request);
        String usernmae=JwtTokenUtils.getUsername(token);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        String[] list= StringUtils.split(permission,",");
        return  model;
    }


}
