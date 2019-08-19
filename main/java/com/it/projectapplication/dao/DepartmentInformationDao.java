package com.it.projectapplication.dao;

import com.it.projectapplication.domain.DepartmentInformation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentInformationDao extends PagingAndSortingRepository<DepartmentInformation,Long> {

}
