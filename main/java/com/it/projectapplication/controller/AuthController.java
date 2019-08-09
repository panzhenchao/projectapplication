package com.it.projectapplication.controller;

import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @PostMapping("/loginTest")
    public  void login(HttpServletRequest request, HttpServletResponse response) throws JSONException {
//        User user =new User();
//        user.setUsername("admin");
//        user.setPassword("admin1");
//        String url="http://localhost:8080/projectapplication/login";
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.putOpt("username",user.getUsername());
//        jsonObject.putOpt("password",user.getPassword());
//        String token=RestTemplateUtils.sendPostRequest(url,jsonObject.toString());
//        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);

    }

}
