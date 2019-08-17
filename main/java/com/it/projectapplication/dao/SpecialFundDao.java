package com.it.projectapplication.dao;

import com.it.projectapplication.domain.SpecialFund;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface  SpecialFundDao extends PagingAndSortingRepository<SpecialFundDao,Long> {


    public void save(SpecialFund specialFund);
}
