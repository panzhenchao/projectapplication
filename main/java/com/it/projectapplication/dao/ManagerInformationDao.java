package com.it.projectapplication.dao;

import com.it.projectapplication.domain.DepartmentInformation;
import com.it.projectapplication.domain.ManagerInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ManagerInformationDao extends PagingAndSortingRepository<ManagerInformation,Long> , CrudRepository<ManagerInformation,Long> {
    public List<ManagerInformation> findManagerInformationsByDepartmentInformation(DepartmentInformation departmentInformation);
    public ManagerInformation findManagerInformationByUsername(String username);

}
