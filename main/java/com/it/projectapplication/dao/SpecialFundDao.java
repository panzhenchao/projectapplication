package com.it.projectapplication.dao;

import com.it.projectapplication.domain.SpecialFund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface  SpecialFundDao extends PagingAndSortingRepository<SpecialFund,Long> {
    @Override
    Page<SpecialFund> findAll(Pageable pageable);

    public SpecialFund findSpecialFundByName(String name);

    Page<SpecialFund> findSpecialFundsByDepartmentNameIsLike(String department,Pageable pageable);



}
