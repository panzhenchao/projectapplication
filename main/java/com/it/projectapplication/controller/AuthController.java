package com.it.projectapplication.controller;

import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String,String> registerUser){
        User user=new User();
        user.setUsername(registerUser.get("username"));
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        User save=userDao.save(user);
        return save.toString();
    }

}
