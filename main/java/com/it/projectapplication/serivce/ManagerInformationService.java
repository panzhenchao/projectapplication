package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.ManagerInformationDao;
import com.it.projectapplication.domain.DepartmentInformation;
import com.it.projectapplication.domain.ManagerInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerInformationService {
    @Autowired
    ManagerInformationDao managerInformationDao;
    public ManagerInformation findManagerInformationByUserName(String username){

        ManagerInformation managerInformation=managerInformationDao.findManagerInformationByUsername(username);

        return managerInformation;
    }

    public List<ManagerInformation> findManagerInformationByDepartment(DepartmentInformation departmentInformation){

        List<ManagerInformation> list=managerInformationDao.findManagerInformationsByDepartmentInformation(departmentInformation);
        return list;
    }
    public List<ManagerInformation> findAll(){
        List<ManagerInformation> list=(List<ManagerInformation>) managerInformationDao.findAll();
        return list;
    }

}
