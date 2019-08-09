package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public ModelAndView login(ModelAndView model, User user){
        User user1=userDao.findUserByUsername(user.getUsername());
        if(null==user1){
            model.addObject("msg","账号不存在");
            model.setViewName("/all-admin-login");
            return model;
        }else if (!bCryptPasswordEncoder.matches(user.getPassword(),user1.getPassword())){
            model.addObject("msg","账号密码错误");
            model.setViewName("/all-admin-login");
            return model;
        }
        else if ("0".equals(user1.getState())){
            model.addObject("msg","账号未激活");
            model.setViewName("/all-admin-login");
            return model;
        }else if("1".equals(user1.getState())){
            model.setViewName("/main");

        }return model;
    }


}
