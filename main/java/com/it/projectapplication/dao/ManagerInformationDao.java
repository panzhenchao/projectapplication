package com.it.projectapplication.dao;

import com.it.projectapplication.domain.ManagerInformation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ManagerInformationDao extends PagingAndSortingRepository<ManagerInformation,Long> {
    public ManagerInformation findManagerInformationByUsername(String username);

}
