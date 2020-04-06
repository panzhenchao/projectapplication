package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.SpecialProjectDao;
import com.it.projectapplication.domain.SpecialProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialProjectService {
    @Autowired
    SpecialProjectDao specialProjectDao;
    public void saveSpecialProject(SpecialProject specialProject){
        specialProjectDao.save(specialProject);
    }
    public List<SpecialProject> findListByDeclareSubject(Integer typeNumber){
        List<SpecialProject> list=specialProjectDao.findListByDeclare(typeNumber);
        return list;
    }
    public SpecialProject findSpecoalProjectById(Long id){
        SpecialProject specialProject=specialProjectDao.findSpecialProjectById(id);
        return specialProject;
    }
    public SpecialProject findMinStopDate(){
        SpecialProject specialProject=specialProjectDao.findMinStopDate();
        return specialProject;
    }


}
