package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public ModelAndView login(ModelAndView model, User user){
        User user1=userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        if(null==user1){
            model.addObject("msg","账号密码错误");
            model.setViewName("/index");
            return model;
        }else if ("0"==user1.getState()){
            model.addObject("msg","账号未激活");
            model.setViewName("/index");
            return model;
        }else if("1"==user1.getState()){
            model.setViewName("/main");
        }
        return model;
    }

}
