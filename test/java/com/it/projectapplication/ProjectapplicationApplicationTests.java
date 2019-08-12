package com.it.projectapplication;

import com.it.projectapplication.dao.PermissionDao;
import com.it.projectapplication.dao.RoleDao;
import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.Permission;
import com.it.projectapplication.domain.Role;
import com.it.projectapplication.domain.User;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ProjectapplicationApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService service;
    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚

    public void contextLoads() {

         User user=userDao.findUserByUsername("admin");
         Role role=new Role();
         role.setName("ROLE_ADMIN");
         Permission permission=new Permission();
         permission.setName("ROLE_ADMIN");
         permission.setDescritpion("ROLE_ADMIN");
         user.getRoles().add(role);

         role.getPermissions().add(permission);

         userDao.save(user);
         permissionDao.save(permission);





    }
    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void castTest(){
        List<Permission> list=permissionDao.findAll();
        System.out.println(list);
    }
    @Test
    public void testRandom(){
        User user=new User();
        user.setUsername("1231");
        user.setPassword("123");
        User user1= userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        System.out.println(user1);
    }
    @Test
    public void testPassword(){
        User user=userDao.findUserByUsername("admin");
        boolean f=bCryptPasswordEncoder.matches("admin1",user.getPassword());
        System.out.println(f);
    }
    @Test
    public void testCookie(){
        String a="Bearer+aaaaa";
        String b=a.replace(JwtTokenUtils.TOKEN_COOKIE_PREFIX,JwtTokenUtils.TOKEN_PREFIX);
        System.out.println(b);
        String c=b.replace(JwtTokenUtils.TOKEN_PREFIX,"");
        System.out.println(c);
    }




}
