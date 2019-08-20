package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.ProjectDao;
import com.it.projectapplication.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
