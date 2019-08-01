package com.it.projectapplication;

import com.it.projectapplication.dao.RoleDao;
import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.Role;
import com.it.projectapplication.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ProjectapplicationApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚

    public void contextLoads() {

         User user=new User();
         user.setUsername("2");
         user.setPassword("2");
         Role role=new Role();
         role.setName("22222");
         user.getRoles().add(role);
         role.getUsers().add(user);

         userDao.save(user);




    }
    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void castTest(){
        User user=userDao.findUserById(15L);
        Set<Role> roles=user.getRoles();
        for (Role role:roles){
            System.out.println(role.getName());
        }


    }

}
