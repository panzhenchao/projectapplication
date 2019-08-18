package com.it.projectapplication.controller;

import com.it.projectapplication.domain.SpecialFund;
import com.it.projectapplication.domain.SpecialProject;
import com.it.projectapplication.serivce.SpecialFundService;
import com.it.projectapplication.serivce.SpecialProjectService;
import com.it.projectapplication.utils.JwtTokenUtils;
import com.it.projectapplication.utils.SaveUploadUtils;
import com.it.projectapplication.utils.StringSplit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SpecialManageController {
    @Autowired
    SpecialFundController specialFundController;
    @Autowired
    SpecialProjectService specialProjectService;
    @Autowired
    SpecialFundService specialFundService;
    @RequestMapping(value = "/addSpecialProject")
    public ModelAndView addSpecialProject(ModelAndView model, HttpServletRequest request, @RequestParam("fundName") String fundName){
        String token= JwtTokenUtils.getToken(request);
        model.addObject("permission",JwtTokenUtils.getUserPermission(token));
        model.addObject("fundName",fundName);
        model.setViewName("special-project-subject-registration");
        return model;
    }
    @RequestMapping(value ="/successAddSpecialProject")
    public ModelAndView successSpecialProject(ModelAndView model, HttpServletRequest request, SpecialProject specialProject,@RequestParam("undertakLetterDateFile") MultipartFile file,@RequestParam("checkBox") String checkBox,@RequestParam("fundName") String fundName){
       if(null==checkBox){
           specialProject.setDeclareSubject(0);
       }else {
           specialProject.setDeclareSubject(StringSplit.stringSplit(checkBox));
       }
        SpecialFund specialFund=specialFundService.findSpecialFundByName(fundName);
        specialProject.setUndertakLetterFile(SaveUploadUtils.getSaveDirFile(file,SaveUploadUtils.SPECIAL_PROJECT_PAPER));
        specialProject.setSpecialFund(specialFund);
        specialProject.setState("0");
        specialProjectService.saveSpecialProject(specialProject);
        specialFundController.specialManage(model,request,1,5);
        return model;
    }

}
