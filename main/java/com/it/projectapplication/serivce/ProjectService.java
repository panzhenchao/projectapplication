package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.ProjectDao;
import com.it.projectapplication.domain.Project;
import com.it.projectapplication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectDao projectDao;

    public  void save(Project project){
        projectDao.save(project);
    }

    public  Long findMaxNumber(){
        Long maxNumber=projectDao.findMaxNumber();
        return maxNumber;
    }
    public Project findProjectById(Long id){
        Project project=projectDao.findProjectByid(id);
        return project;
    }
    public List<Project> findProjectByUser(User user){
        List<Project> list=projectDao.findProjectsByUser(user);
        return list;
    }
    public List<Project> findProjectsByAddressIsLike(String address){
        List<Project> list=projectDao.findProjectsByAddressIsLike(address);
        return list;
    }

}
