package com.it.projectapplication;

import com.it.projectapplication.dao.*;
import com.it.projectapplication.domain.*;
import com.it.projectapplication.serivce.ManagerInformationService;
import com.it.projectapplication.serivce.PersonalInformationService;
import com.it.projectapplication.serivce.SpecialProjectService;
import com.it.projectapplication.serivce.UserService;
import com.it.projectapplication.utils.JwtTokenUtils;
import com.it.projectapplication.utils.ProjectNumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    PersonalInformationService personalInformationService;
    @Autowired
    SpecialProjectDao specialProjectDao;
    @Autowired
    SpecialProjectService specialProjectService;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    ManagerInformationService managerInformationService;


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
    @Test
    public void testFindINformation(){
        PersonalInformation personalInformation=personalInformationService.findPersonalInformationByUsername("admin");
        System.out.println(personalInformation);
    }
    @Test
    public void testFindPersonalPage(){
        Pageable pageable=new PageRequest(0,5);
        Page<PersonalInformation> page=personalInformationService.findPersonalInformationByaddressAndPageble("%灵安镇%",pageable);
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());
    }
    @Test
    public  void testTypeNumber(){
        List<SpecialProject> specialProjects=specialProjectService.findListByDeclareSubject(1);
        System.out.println(specialProjects.size());
        for (SpecialProject specialProject:specialProjects){
            System.out.println(specialProject.getYear());
        }
    }
    @Test
    public void testProjectNumber(){
       Long a= ProjectNumberUtils.getProjectNumber(null);
       System.out.println(a);
    }
    @Test
    public  void testfindMaxId(){
        Date date=new Date(System.currentTimeMillis());
        System.out.println(date);
    }
    @Test
    public  void testfindss(){
        User user=userDao.findUserByUsername("admin");
        List<Project> list=projectDao.findProjectsByUser(user);
    }
    @Test
    public void testfindsss(){
        ManagerInformation managerInformation=managerInformationService.findManagerInformationByUserName("admin");

    }
    @Test
    public  void testMinStopDate(){
        SpecialProject s=specialProjectDao.findMinStopDate();
        System.out.println(s.getStopDate());
    }
    @Test
    public void testDate() throws Exception{
        SpecialProject specialProject=specialProjectService.findMinStopDate();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       Date spTime=sdf.parse(sdf.format(specialProject.getStopDate()));
       Date tTime=sdf.parse(sdf.format(new Date()));
        System.out.println(spTime);
        System.out.println(tTime);




        if(spTime.before(tTime)){
            System.out.println(1);
        }else if(spTime.after(tTime)){
            System.out.println(2);
        }else {
            System.out.println(3);
        }
    }



}
