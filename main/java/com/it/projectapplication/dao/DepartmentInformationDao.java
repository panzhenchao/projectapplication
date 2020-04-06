package com.it.projectapplication.dao;

import com.it.projectapplication.domain.DepartmentInformation;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DepartmentInformationDao extends PagingAndSortingRepository<DepartmentInformation,Long> {
    public Optional<DepartmentInformation> findDepartmentInformationByDepartmentName(String departmentName);

}
