package com.it.projectapplication;

import com.it.projectapplication.dao.PermissionDao;
import com.it.projectapplication.dao.RoleDao;
import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.Permission;
import com.it.projectapplication.domain.Role;
import com.it.projectapplication.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

}
