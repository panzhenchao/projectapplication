package com.it.projectapplication.dao;

import com.it.projectapplication.domain.PersonalInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonalInformationDao extends PagingAndSortingRepository<PersonalInformation,Long> {
    public PersonalInformation findPersonalInformationByUsername(String username);

    public Page<PersonalInformation> findPersonalInformationsByAddressIsLike(String address, Pageable pageable);
}
