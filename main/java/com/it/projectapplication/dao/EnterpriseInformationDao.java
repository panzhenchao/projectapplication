package com.it.projectapplication.dao;

import com.it.projectapplication.domain.EnterpriseInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnterpriseInformationDao extends PagingAndSortingRepository<EnterpriseInformation,Long> {
    public EnterpriseInformation findEnterpriseInformationByUsername(String username);

    public Page<EnterpriseInformation> findEnterpriseInformationsByAddressIsLike(String address, Pageable pageable);
}
