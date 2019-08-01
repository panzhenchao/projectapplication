package com.it.projectapplication.controller;

import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(){
        User user=new User();
        user.setUsername(("username"));
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        User save=userDao.save(user);
        return save.toString();
    }

}
