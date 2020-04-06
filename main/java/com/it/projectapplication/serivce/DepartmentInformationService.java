package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.DepartmentInformationDao;
import com.it.projectapplication.domain.DepartmentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentInformationService {
    @Autowired
    DepartmentInformationDao departmentInformationDao;
    public DepartmentInformation findDepartmentInformationById(Long id){
        Optional<DepartmentInformation> optional =departmentInformationDao.findById(id);
        if(optional.isPresent()){
            DepartmentInformation departmentInformation=optional.get();
            return departmentInformation;
        }
        return null;

    }
    public void save(DepartmentInformation departmentInformation){
        if(departmentInformation!=null){
            departmentInformationDao.save(departmentInformation);
        }
    }
    public  DepartmentInformation findDepartmentByDepartmentName(String departmentName){
        Optional<DepartmentInformation> optional=departmentInformationDao.findDepartmentInformationByDepartmentName(departmentName);
        if(optional.isPresent()){
            DepartmentInformation departmentInformation=optional.get();
            return departmentInformation;
        }else {
            return null;
        }

    }
    public List<DepartmentInformation> findAllDepartment(){
        List<DepartmentInformation> list=(List<DepartmentInformation>) departmentInformationDao.findAll();
        return list;
    }
}
