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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

         User user=new User();
         user.setUsername("2");
         user.setPassword("2");
         Role role=new Role();
         role.setName("22222");
        Permission permission=new Permission();
        permission.setName("ceshi");
        permission.setDescritpion("ceshi");
         user.getRoles().add(role);
         role.getUsers().add(user);
         role.getPermissions().add(permission);

         userDao.save(user);
         permissionDao.save(permission);





    }
    @Test
    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    public void castTest(){
        User user=userDao.findUserById(25L);
        Set<Role> roles=user.getRoles();
        List<String> permissionList=new ArrayList<>();
        for(Role role:roles){
            Set<Permission> permissions=role.getPermissions();
            for(Permission permission:permissions){
                permissionList.add(permission.getName());
            }
        }

        System.out.println(permissionList);
    }

}
