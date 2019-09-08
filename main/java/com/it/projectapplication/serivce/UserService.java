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
        else if (0==user1.getState()){
            model.addObject("msg","账号未激活");
            model.setViewName("/all-admin-login");
            return model;
        }else if(1==user1.getState()){
            model.setViewName("/main");

        }return model;
    }
    public String findCategory(String username){
        User user=userDao.findUserByUsername(username);
        return user.getCategory();
    }
    public boolean validatePassword(String username,String password){
        User user=userDao.findUserByUsername(username);
        return bCryptPasswordEncoder.matches(password,user.getPassword());
    }
    public void changePassword(String username,String password){
        User user=userDao.findUserByUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userDao.save(user);
    }
    public void saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
    public Integer findStateByUsername(String username){
        User user=userDao.findUserByUsername(username);
        return user.getState();
    }
    public User findUserByUsername(String username){
        User user=userDao.findUserByUsername(username);
        return user;
    }


}
