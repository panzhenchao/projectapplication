package com.it.projectapplication.dao;

import com.it.projectapplication.domain.ManagerInformation;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ManagerInformationDao extends PagingAndSortingRepository<ManagerInformation,Long> {
    public ManagerInformation findManagerInformationByUsername(String username);
    public List<ManagerInformation> findManagerInformationsByDepartment(String department);

}
